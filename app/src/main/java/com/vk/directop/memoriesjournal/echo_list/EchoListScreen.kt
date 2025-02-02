package com.vk.directop.memoriesjournal.echo_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vk.directop.memoriesjournal.echo_list.components.EchoList
import com.vk.directop.memoriesjournal.echo_list.components.EmptyList
import com.vk.directop.memoriesjournal.echo_list.components.GradientFAB
import com.vk.directop.memoriesjournal.echo_list.components.RecordingBottomSheet
import com.vk.directop.memoriesjournal.echo_list.models.ItemUi
import com.vk.directop.memoriesjournal.echo_list.models.Mood
import com.vk.directop.memoriesjournal.ui.theme.InterFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EchoListScreen(
    state: EchoListState,
    onAction: (EchoListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            GradientFAB { isSheetOpen = true }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Your EchoJournal",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = InterFontFamily,
                modifier = Modifier.padding(16.dp)
            )
            if (state.records.isEmpty()) {
                EmptyList()
            } else {
                EchoList(
                    records = state.records,
                    onAction = onAction,
                )
            }
        }
        if (isSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = { isSheetOpen = false },
                sheetState = sheetState
            ) {
                RecordingBottomSheet(
                    isRecording = state.isRecording,
                    isPaused = state.isPaused,
                    elapsedTime = "0:00",
                    onAction = { action ->
                        onAction(action)
                        when (action) {
                            is EchoListAction.OnCloseBottomSheet -> {
                                isSheetOpen = false
                            }

                            else -> {}
                        }
                    }
                )
            }
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
                    isRecording = true,
                    isPaused = true,
                    records = recordsPreview
                ),
                onAction = {},
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }
}

val recordsPreview = listOf(
    ItemUi(
        id = "232323",
        description = "My first Entry",
        mood = Mood.EXCITED,
        createdAt = "12/03/25 15:20",
        tags = listOf("sds", "dsfdfg"),
        filePath = "depath of file"
    ),
    ItemUi(
        id = "232323",
        description = "My Entry",
        mood = Mood.NEUTRAL,
        createdAt = "12/03/25 15:20",
        tags = listOf("sds", "dsfdfg"),
        filePath = "depath of file"
    ),
    ItemUi(
        id = "232323",
        description = "My third Entry",
        mood = Mood.SAD,
        createdAt = "12/03/25 15:20",
        tags = listOf("sds", "dsfdfg"),
        filePath = "depath of file"
    ),
    ItemUi(
        id = "232323",
        description = "My fourth Entry",
        mood = Mood.STRESSED,
        createdAt = "12/03/25 15:20",
        tags = listOf("sds", "dsfdfg"),
        filePath = "depath of file"
    ),
    ItemUi(
        id = "232323",
        description = "My Entry",
        mood = Mood.EXCITED,
        createdAt = "12/03/25 15:20",
        tags = listOf("sds", "dsfdfg"),
        filePath = "depath of file"
    ),
    ItemUi(
        id = "232323",
        description = "My first Entry",
        mood = Mood.EXCITED,
        createdAt = "12/03/25 15:20",
        tags = listOf("sds", "dsfdfg"),
        filePath = "depath of file"
    ),
    ItemUi(
        id = "232323",
        description = "My Entry",
        mood = Mood.NEUTRAL,
        createdAt = "12/03/25 15:20",
        tags = listOf("sds", "dsfdfg"),
        filePath = "depath of file"
    ),
    ItemUi(
        id = "232323",
        description = "My third Entry",
        mood = Mood.SAD,
        createdAt = "12/03/25 15:20",
        tags = listOf("sds", "dsfdfg"),
        filePath = "depath of file"
    ),
    ItemUi(
        id = "232323",
        description = "My fourth Entry",
        mood = Mood.STRESSED,
        createdAt = "12/03/25 15:20",
        tags = listOf("sds", "dsfdfg"),
        filePath = "depath of file"
    ),
    ItemUi(
        id = "232323",
        description = "My Entry",
        mood = Mood.EXCITED,
        createdAt = "12/03/25 15:20",
        tags = listOf("sds", "dsfdfg"),
        filePath = "depath of file"
    ),
)