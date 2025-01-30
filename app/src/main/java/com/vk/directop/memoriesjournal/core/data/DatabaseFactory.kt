package com.vk.directop.memoriesjournal.core.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class DatabaseFactory(
    private val context: Context
) {
    fun create(): RoomDatabase.Builder<AudioRecordsDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(AudioRecordsDatabase.DB_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}