package com.vk.directop.memoriesjournal.echo_list

import androidx.compose.runtime.Immutable
import com.vk.directop.memoriesjournal.echo_list.models.ItemUi

@Immutable
data class EchoListState(
    val records: List<ItemUi> = emptyList()
)
