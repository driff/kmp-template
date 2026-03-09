package com.driff.template.domain.repository

import arrow.core.Either
import com.driff.template.core.error.AppError
import com.driff.template.domain.model.Message

interface MessageRepository {
    suspend fun getMessages(): Either<AppError, List<Message>>
}
