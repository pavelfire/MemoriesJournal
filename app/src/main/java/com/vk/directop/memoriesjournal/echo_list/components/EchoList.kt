package com.vk.directop.memoriesjournal.echo_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vk.directop.memoriesjournal.echo_list.EchoListAction
import com.vk.directop.memoriesjournal.echo_list.models.ItemUi

@Composable
fun EchoList(
    records: List<ItemUi>,
    onAction: (EchoListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier.padding(16.dp)) {
        items(records) { record ->
            EchoItem(
                item = record,
                onAction = onAction
            )
        }
    }
}