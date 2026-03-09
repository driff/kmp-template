package com.driff.template.data.local.datasource

import arrow.core.Either
import com.driff.template.core.error.AppError
import com.driff.template.data.local.entity.MessageEntity

interface LocalMessageDataSource {
    suspend fun getMessages(): Either<AppError, List<MessageEntity>>
    suspend fun saveMessages(entities: List<MessageEntity>): Either<AppError, Unit>
}
