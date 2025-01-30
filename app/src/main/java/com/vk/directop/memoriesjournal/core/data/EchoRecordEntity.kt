package com.vk.directop.memoriesjournal.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.vk.directop.memoriesjournal.echo_list.models.Mood

@Entity
@TypeConverters(MoodConverter::class, TagsConverter::class)
data class EchoRecordEntity(
    @PrimaryKey val id: String,
    val description: String,
    val filePath: String,
    val createdAt: Long,
    val mood: Mood,
    val tags: List<String>
)