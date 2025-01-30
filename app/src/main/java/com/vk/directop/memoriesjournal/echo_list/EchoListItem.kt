package com.vk.directop.memoriesjournal.echo_list

import androidx.compose.runtime.Immutable
import com.vk.directop.memoriesjournal.R
import com.vk.directop.memoriesjournal.core.data.EchoRecordEntity

@Immutable
data class EchoListItem(
//    val title: String = "My Entry",
//    val mood: Mood = Mood.EXCITED
    val description: String,
    val filePath: String,
    val createdAt: Long,
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

fun EchoRecordEntity.toItemListState(): EchoListItem = EchoListItem(
    description = description,
    filePath = filePath,
    createdAt = createdAt,
    mood = mood,
    tags = tags
)