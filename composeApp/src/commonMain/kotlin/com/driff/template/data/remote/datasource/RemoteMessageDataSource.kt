package com.driff.template.data.remote.datasource

import arrow.core.Either
import com.driff.template.core.error.AppError
import com.driff.template.data.remote.dto.MessageDto

interface RemoteMessageDataSource {
    suspend fun getMessages(): Either<AppError, List<MessageDto>>
}
