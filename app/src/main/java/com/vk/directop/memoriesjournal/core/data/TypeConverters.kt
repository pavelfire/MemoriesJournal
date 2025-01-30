package com.vk.directop.memoriesjournal.core.data

import androidx.room.TypeConverter
import com.vk.directop.memoriesjournal.echo_list.Mood

class MoodConverter {
    @TypeConverter
    fun fromMood(mood: Mood): String {
        return mood.name
    }

    @TypeConverter
    fun toMood(value: String): Mood {
        return Mood.valueOf(value)
    }
}

class TagsConverter {
    @TypeConverter
    fun fromTagsList(tags: List<String>): String {
        return tags.joinToString(",")
    }

    @TypeConverter
    fun toTagsList(value: String): List<String> {
        return if (value.isEmpty()) emptyList() else value.split(",")
    }
}