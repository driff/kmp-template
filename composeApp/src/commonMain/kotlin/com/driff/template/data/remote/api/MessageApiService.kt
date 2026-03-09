package com.driff.template.data.remote.api

import com.driff.template.data.remote.dto.MessageDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import me.tatarka.inject.annotations.Inject

@Inject
class MessageApiService(
    private val client: HttpClient,
) {
    suspend fun getMessages(): List<MessageDto> =
        client.get("/messages").body()
}
