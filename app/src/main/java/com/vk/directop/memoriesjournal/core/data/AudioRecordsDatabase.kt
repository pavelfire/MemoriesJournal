package com.vk.directop.memoriesjournal.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [EchoRecordEntity::class],
    version = 1
)
@TypeConverters(MoodConverter::class, TagsConverter::class)
abstract class AudioRecordsDatabase: RoomDatabase() {
    abstract val echoDao: EchoDao

    companion object {
        const val DB_NAME = "audio_records.db"
    }
}