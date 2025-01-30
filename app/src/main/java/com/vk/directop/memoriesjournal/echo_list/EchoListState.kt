package com.vk.directop.memoriesjournal.echo_list

import androidx.compose.runtime.Immutable
import com.vk.directop.memoriesjournal.echo_list.models.ItemUi

@Immutable
data class EchoListState(
    val isRecording: Boolean = false,
    val isPaused: Boolean = false,
    val records: List<ItemUi> = emptyList()
)
