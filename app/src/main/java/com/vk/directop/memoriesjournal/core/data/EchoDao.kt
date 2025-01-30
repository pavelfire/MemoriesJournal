package com.vk.directop.memoriesjournal.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EchoDao {
    @Insert
    suspend fun insertRecord(record: EchoRecordEntity)

    @Query("SELECT * FROM EchoRecordEntity")
    suspend fun getAllRecords(): List<EchoRecordEntity>
}
