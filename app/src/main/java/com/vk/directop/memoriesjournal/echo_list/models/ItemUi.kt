package com.vk.directop.memoriesjournal.echo_list.models

import androidx.compose.runtime.Immutable
import com.vk.directop.memoriesjournal.R
import com.vk.directop.memoriesjournal.core.data.EchoRecordEntity
import com.vk.directop.memoriesjournal.core.presentation.util.toFormattedDate

@Immutable
data class ItemUi(
    val id: String,
    val isPlaying: Boolean = false,
    val currentTime: String = "",
    val totalTime: String = "",
    val progress: Float = 0.0f,
    val description: String,
    val filePath: String,
    val createdAt: String,
    val mood: Mood,
    val tags: List<String>
)

enum class Mood {
    EXCITED,
    NEUTRAL,
    PEACEFUL,
    SAD,
    STRESSED;

    fun toDrawableRes(): Int {
        return when (this) {
            EXCITED -> R.drawable.mo_excited
            NEUTRAL -> R.drawable.mo_neutral
            PEACEFUL -> R.drawable.mo_peaceful
            SAD -> R.drawable.mo_sad
            STRESSED -> R.drawable.mo_stressed
        }
    }
}

fun EchoRecordEntity.toItemListState(): ItemUi = ItemUi(
    id = id,
    description = description,
    filePath = filePath,
    createdAt = createdAt.toFormattedDate(),
    mood = mood,
    tags = tags
)