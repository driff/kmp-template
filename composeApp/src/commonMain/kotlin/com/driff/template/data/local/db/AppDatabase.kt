package com.driff.template.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.driff.template.data.local.dao.MessageDao
import com.driff.template.data.local.entity.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}
