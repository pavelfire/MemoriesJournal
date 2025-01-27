package com.vk.directop.memoriesjournal.echo_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vk.directop.memoriesjournal.echo_list.components.EchoList
import com.vk.directop.memoriesjournal.echo_list.components.EmptyList

@Composable
fun EchoListScreen(
    state: EchoListState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Your EchoJournal"
        )
        if (state.records.isEmpty()) {
            EmptyList()
        } else {
            EchoList(state.records)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun EchoListScreenPreview() {
    MaterialTheme {
        Scaffold { innerPadding ->
            EchoListScreen(
                state = EchoListState(
                    recordsPreview
                ),
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            )
        }
    }
}

val recordsPreview = listOf(
    EchoListItemState(
        title = "My first Entry",
        mood = Mood.EXCITED
    ),
    EchoListItemState(
        title = "My Entry",
        mood = Mood.NEUTRAL
    ),
    EchoListItemState(
        title = "My third Entry",
        mood = Mood.SAD
    ),
    EchoListItemState(
        title = "My fourth Entry",
        mood = Mood.STRESSED
    ),
    EchoListItemState(
        title = "My Entry",
        mood = Mood.EXCITED
    ),

    )