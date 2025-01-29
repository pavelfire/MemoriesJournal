package com.vk.directop.memoriesjournal

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.vk.directop.memoriesjournal.playback.AndroidAudioPlayer
import com.vk.directop.memoriesjournal.recorder.AndroidAudioRecorder
import com.vk.directop.memoriesjournal.ui.theme.MemoriesJournalTheme
import java.io.File

class MainActivity : ComponentActivity() {

    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }

    private var audioFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )
        enableEdgeToEdge()
        setContent {
            MemoriesJournalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            File(cacheDir, "audio.mp3").also {
                                recorder.start(it)
                                audioFile = it
                            }
                        }) {
                            Text(text = "Start recording")
                        }

                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            recorder.stop()
                        }) {
                            Text(text = "Stop recording")
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            player.playFile(audioFile ?: return@Button)
                        }) {
                            Text(text = "Start play")
                        }

                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            player.stop()
                        }) {
                            Text(text = "Stop play")
                        }
                    }
                }
            }
        }
    }
}

