package com.driff.template.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    @SerialName("id") val id: Int,
    @SerialName("author") val author: String,
    @SerialName("body") val body: String,
    @SerialName("created_at") val createdAt: String,
)
