package com.vk.directop.memoriesjournal.echo_list

import androidx.compose.runtime.Immutable
import com.vk.directop.memoriesjournal.R

@Immutable
data class EchoListItemState(
    val title: String = "My Entry",
    val mood: Mood = Mood.EXCITED
)

enum class Mood{
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