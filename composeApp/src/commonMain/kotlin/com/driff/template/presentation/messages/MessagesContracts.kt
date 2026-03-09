package com.driff.template.presentation.messages

data class MessagesUiState(
    val isLoading: Boolean = false,
    val messages: List<String> = emptyList(),
    val inputText: String = "",
)

sealed interface MessagesActions {
    data class OnInputChange(val text: String) : MessagesActions
    data object OnSendMessage : MessagesActions
}

sealed interface MessagesEffects {
    data object ScrollToBottom : MessagesEffects
}
