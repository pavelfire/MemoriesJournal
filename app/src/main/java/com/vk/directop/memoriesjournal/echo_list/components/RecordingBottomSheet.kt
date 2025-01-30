package com.vk.directop.memoriesjournal.echo_list.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.vk.directop.memoriesjournal.echo_list.EchoListAction

@Composable
fun RecordingBottomSheet(
    onClose: () -> Unit,
    onAction: (EchoListAction) -> Unit
) {
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
            onClose = {},
            onAction = {}
        )
    }
}