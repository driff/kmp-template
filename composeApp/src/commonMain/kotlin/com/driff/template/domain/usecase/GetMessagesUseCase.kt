package com.driff.template.domain.usecase

import arrow.core.Either
import com.driff.template.core.error.AppError
import com.driff.template.domain.model.Message
import com.driff.template.domain.repository.MessageRepository
import me.tatarka.inject.annotations.Inject

@Inject
class GetMessagesUseCase(
    private val repository: MessageRepository,
) {
    suspend operator fun invoke(): Either<AppError, List<Message>> =
        repository.getMessages()
}
