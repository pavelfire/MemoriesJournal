package com.vk.directop.memoriesjournal

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vk.directop.memoriesjournal.echo_list.EchoListScreen
import com.vk.directop.memoriesjournal.echo_list.EchoListScreenViewModel
import com.vk.directop.memoriesjournal.ui.theme.MemoriesJournalTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            MemoriesJournalTheme {
                val viewModel = koinViewModel<EchoListScreenViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                EchoListScreen(
                    state = state,
                    onAction = { action ->
                        viewModel.onAction(action)
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                )
            }
        }
    }
}

