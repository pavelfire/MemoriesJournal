package com.vk.directop.memoriesjournal.echo_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vk.directop.memoriesjournal.echo_list.EchoListItemState

@Composable
fun EchoList(
    records: List<EchoListItemState>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(records) { record ->
            EchoItem(item = record)
        }
    }
}