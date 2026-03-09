package com.driff.template.presentation.messages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.driff.template.di.createAppComponent
import com.driff.template.domain.usecase.GetMessagesUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class MessagesViewModel(
    private val getMessagesUseCase: GetMessagesUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            getMessagesUseCase().fold(
                {},
                ifRight = { response ->
                    val messages = response.fold(mutableListOf<String>()) { acc, entity ->
                        acc.add(entity.body)
                        acc
                    }
                    _uiState.update {
                        it.copy(
                            messages = it.messages + messages
                        )
                    }
                }
            )
        }
    }

    private val _uiState = MutableStateFlow(MessagesUiState())
    val uiState: StateFlow<MessagesUiState> = _uiState.asStateFlow()

    private val _effects = Channel<MessagesEffects>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun onAction(action: MessagesActions) {
        when (action) {
            is MessagesActions.OnInputChange -> {
                _uiState.update { it.copy(inputText = action.text) }
            }
            MessagesActions.OnSendMessage -> {
                val text = _uiState.value.inputText.trim()
                if (text.isBlank()) return
                _uiState.update { state ->
                    state.copy(
                        messages = state.messages + text,
                        inputText = "",
                    )
                }
                viewModelScope.launch {
                    _effects.send(MessagesEffects.ScrollToBottom)
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                createAppComponent().messagesViewModel
            }
        }
    }
}
