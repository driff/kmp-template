package com.driff.template.data.repository

import arrow.core.Either
import arrow.core.flatMap
import com.driff.template.core.error.AppError
import com.driff.template.data.local.datasource.LocalMessageDataSource
import com.driff.template.data.mapper.toDomain
import com.driff.template.data.mapper.toEntity
import com.driff.template.data.remote.datasource.RemoteMessageDataSource
import com.driff.template.domain.model.Message
import com.driff.template.domain.repository.MessageRepository
import me.tatarka.inject.annotations.Inject

@Inject
class MessageRepositoryImpl(
    private val remoteDataSource: RemoteMessageDataSource,
    private val localDataSource: LocalMessageDataSource,
) : MessageRepository {

    override suspend fun getMessages(): Either<AppError, List<Message>> {
        val local = localDataSource.getMessages()
        val localValues = local.getOrNull()

        return if (!localValues.isNullOrEmpty()) {
            Either.Right(localValues.map { it.toDomain() })
        } else {
            remoteDataSource.getMessages()
                .flatMap { dtos ->
                    val entities = dtos.map { it.toDomain().toEntity() }
                    localDataSource.saveMessages(entities).map { dtos.map { dto -> dto.toDomain() } }
                }
        }
    }
}
