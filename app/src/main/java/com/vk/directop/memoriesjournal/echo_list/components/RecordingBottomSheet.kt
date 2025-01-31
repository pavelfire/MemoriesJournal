package com.vk.directop.memoriesjournal.echo_list.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vk.directop.memoriesjournal.R
import com.vk.directop.memoriesjournal.echo_list.EchoListAction

@Composable
fun RecordingBottomSheet(
    isRecording: Boolean,
    isPaused: Boolean,
    elapsedTime: String,
    onAction: (EchoListAction) -> Unit,
) {
    val context = LocalContext.current
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
                isRecording && isPaused -> "Pause..."
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
            text = elapsedTime,
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
                onClick = { onAction(EchoListAction.OnCloseBottomSheet) }
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
                        isPaused && isRecording -> onAction(EchoListAction.OnPauseRecord)
                        isPaused -> onAction(EchoListAction.OnPauseRecord)
                        isRecording -> {
                            onAction(EchoListAction.OnStopRecord)
                            onAction(EchoListAction.OnSaveRecord("Night marathon"))
                            Toast.makeText(context, "Recording Saved", Toast.LENGTH_SHORT).show()
                            onAction(EchoListAction.OnCloseBottomSheet)
                        }

                        else -> onAction(EchoListAction.OnStartRecord)
                    }
                }
            ) {
                Image(
                    imageVector = when {
                        isPaused && isRecording -> ImageVector.vectorResource(id = R.drawable.microphone)
                        isRecording -> ImageVector.vectorResource(id = R.drawable.record_finish)
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
                        isPaused && isRecording -> onAction(EchoListAction.OnStopRecord)
                        isRecording -> onAction(EchoListAction.OnPauseRecord)
                        isPaused -> onAction(EchoListAction.OnStartRecord)
                        else -> onAction(EchoListAction.OnCloseBottomSheet)
                    }
                }
            ) {
                Image(
                    imageVector = when {
                        isPaused && isRecording -> ImageVector.vectorResource(id = R.drawable.check_button)

                        isRecording -> ImageVector.vectorResource(id = R.drawable.pause)

                        isPaused -> ImageVector.vectorResource(id = R.drawable.check_button)

                        else -> ImageVector.vectorResource(id = R.drawable.check_button)
                    },
                    contentDescription = "record control 3"
                )
            }
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
            elapsedTime = "545L",
            onAction = {}
        )
    }
}
