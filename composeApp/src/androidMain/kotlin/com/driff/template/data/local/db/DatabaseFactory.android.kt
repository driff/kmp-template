package com.driff.template.data.local.db

import androidx.room.Room
import androidx.room.RoomDatabase
import com.driff.template.TemplateApplication

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = TemplateApplication.appContext.getDatabasePath("messages.db")
    return Room.databaseBuilder<AppDatabase>(
        context = TemplateApplication.appContext,
        name = dbFile.absolutePath,
    )
}
