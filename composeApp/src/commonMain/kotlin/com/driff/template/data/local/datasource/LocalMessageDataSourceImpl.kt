package com.driff.template.data.local.datasource

import arrow.core.Either
import arrow.core.catch
import com.driff.template.core.error.AppError
import com.driff.template.data.local.dao.MessageDao
import com.driff.template.data.local.entity.MessageEntity
import me.tatarka.inject.annotations.Inject

@Inject
class LocalMessageDataSourceImpl(
    private val dao: MessageDao,
) : LocalMessageDataSource {

    override suspend fun getMessages(): Either<AppError, List<MessageEntity>> =
        Either.catch { dao.getAllMessages() }
            .mapLeft { AppError.Local(it) }

    override suspend fun saveMessages(entities: List<MessageEntity>): Either<AppError, Unit> =
        Either.catch { dao.upsertMessages(entities) }
            .mapLeft { AppError.Local(it) }
}
