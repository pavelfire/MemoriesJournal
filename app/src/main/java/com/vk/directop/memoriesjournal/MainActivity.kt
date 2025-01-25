package com.vk.directop.memoriesjournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.vk.directop.memoriesjournal.echo_list.EchoListScreen
import com.vk.directop.memoriesjournal.echo_list.EchoListState
import com.vk.directop.memoriesjournal.echo_list.recordsPreview
import com.vk.directop.memoriesjournal.ui.theme.DarkBackground
import com.vk.directop.memoriesjournal.ui.theme.MemoriesJournalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoriesJournalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EchoListScreen(
                        state = EchoListState(recordsPreview),
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

