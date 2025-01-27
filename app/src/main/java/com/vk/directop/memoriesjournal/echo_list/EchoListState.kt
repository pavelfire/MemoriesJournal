package com.vk.directop.memoriesjournal.echo_list

import androidx.compose.runtime.Immutable

@Immutable
data class EchoListState(
    val records: List<EchoListItemState> = emptyList()
)
