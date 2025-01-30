package com.vk.directop.memoriesjournal.echo_list.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vk.directop.memoriesjournal.R
import com.vk.directop.memoriesjournal.core.presentation.util.formatTime
import com.vk.directop.memoriesjournal.echo_list.EchoListAction


@Composable
fun RecordingBottomSheet(
    isRecording: Boolean,
    isPaused: Boolean,
    elapsedTime: Long,
    onClose: () -> Unit,
    onAction: (EchoListAction) -> Unit,
) {
    val timeText = formatTime(elapsedTime)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = when {
                isRecording -> "Recording your memories..."
                isPaused -> "Recording paused"
                else -> "Tap to start recording"
            },
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = timeText,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onClose
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.cancel),
                    contentDescription = "Cancel",
                )
            }

            IconButton(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary),
                onClick = {
                    when {
                        isRecording -> onAction(EchoListAction.OnStopRecord)
                        isPaused -> onAction(EchoListAction.OnStartRecord)
                        else -> onAction(EchoListAction.OnStartRecord)
                    }
                }
            ) {
                Image(
                    imageVector = when {
                        isRecording -> ImageVector.vectorResource(id = R.drawable.record_finish)
                        isPaused -> ImageVector.vectorResource(id = R.drawable.microphone)
                        else -> ImageVector.vectorResource(id = R.drawable.microphone)
                    },
                    contentDescription = when {
                        isRecording -> "finish record"
                        isPaused -> "continue record"
                        else -> "start record"
                    },
                )
            }

            IconButton(
                onClick = {
                    when {
                        isRecording -> onAction(EchoListAction.OnStartRecord)
                        isPaused -> onAction(EchoListAction.OnStopRecord)
                        else -> onAction(EchoListAction.OnStartRecord)
                    }
                }
            ) {
                Image(
                    imageVector = when {
                        isRecording -> ImageVector.vectorResource(id = R.drawable.pause)
                        isPaused -> ImageVector.vectorResource(id = R.drawable.check_button)
                        else -> ImageVector.vectorResource(id = R.drawable.check_button)
                    },
                    contentDescription = when {
                        isRecording -> "pause"
                        isPaused -> "finish record"
                        else -> "finish record"
                    }
                )
            }
        }
    }
}

@Composable
fun RecordingBottomSheetOld(
    onClose: () -> Unit,
    onAction: (EchoListAction) -> Unit
) {
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onAction(EchoListAction.OnStartRecord) }) {
            Text("Start Recording")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = { onAction(EchoListAction.OnStopRecord) }) {
            Text("Stop Recording")
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            onAction(EchoListAction.OnSaveRecord(description))
            Toast.makeText(context, "Recording Saved", Toast.LENGTH_SHORT).show()
            onClose()
        }) {
            Text("Save")
        }
    }
}


@PreviewLightDark
@Composable
private fun RecordingBottomSheetPreview() {
    MaterialTheme {
        RecordingBottomSheet(
            isRecording = true,
            isPaused = true,
            elapsedTime = 545L,
            onClose = {},
            onAction = {}
        )
    }
}