package com.driff.template.data.remote.datasource

import arrow.core.Either
import arrow.core.catch
import com.driff.template.core.error.AppError
import com.driff.template.data.remote.api.MessageApiService
import com.driff.template.data.remote.dto.MessageDto
import me.tatarka.inject.annotations.Inject

@Inject
class RemoteMessageDataSourceImpl(
    private val apiService: MessageApiService,
) : RemoteMessageDataSource {

    override suspend fun getMessages(): Either<AppError, List<MessageDto>> =
        Either.catch { apiService.getMessages() }
            .mapLeft { AppError.Network(it) }
}
