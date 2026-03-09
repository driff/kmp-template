package com.driff.template.data.mapper

import com.driff.template.data.local.entity.MessageEntity
import com.driff.template.data.remote.dto.MessageDto
import com.driff.template.domain.model.Message

fun MessageDto.toDomain(): Message = Message(
    id = id,
    author = author,
    body = body,
    createdAt = createdAt,
)

fun MessageEntity.toDomain(): Message = Message(
    id = id,
    author = author,
    body = body,
    createdAt = createdAt,
)

fun Message.toEntity(): MessageEntity = MessageEntity(
    id = id,
    author = author,
    body = body,
    createdAt = createdAt,
)
