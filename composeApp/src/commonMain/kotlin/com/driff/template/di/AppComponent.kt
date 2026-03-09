package com.driff.template.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.driff.template.data.local.dao.MessageDao
import com.driff.template.data.local.datasource.LocalMessageDataSource
import com.driff.template.data.local.datasource.LocalMessageDataSourceImpl
import com.driff.template.data.local.db.AppDatabase
import com.driff.template.data.local.db.getDatabaseBuilder
import com.driff.template.data.remote.datasource.RemoteMessageDataSource
import com.driff.template.data.remote.datasource.RemoteMessageDataSourceImpl
import com.driff.template.data.repository.MessageRepositoryImpl
import com.driff.template.domain.repository.MessageRepository
import com.driff.template.presentation.messages.MessagesViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides

@Component
abstract class AppComponent(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    @Provides
    fun bindMessageRepository(impl: MessageRepositoryImpl): MessageRepository = impl

    @Provides
    fun bindRemoteMessageDataSource(impl: RemoteMessageDataSourceImpl): RemoteMessageDataSource = impl

    @Provides
    fun bindLocalMessageDataSource(impl: LocalMessageDataSourceImpl): LocalMessageDataSource = impl

    abstract val messagesViewModel: MessagesViewModel

    @Provides
    fun provideHttpClient(): HttpClient = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
            filter { request ->
                request.url.host.contains("ktor.io")
            }
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
    }

    @Provides
    fun provideAppDatabase(): AppDatabase = getDatabaseBuilder()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(dispatcher)
        .build()

    @Provides
    fun provideMessageDao(db: AppDatabase): MessageDao = db.messageDao()

}

@KmpComponentCreate
expect fun createAppComponent(): AppComponent