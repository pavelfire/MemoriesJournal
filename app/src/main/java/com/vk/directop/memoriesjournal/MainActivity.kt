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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vk.directop.memoriesjournal.core.navigation.Route
import com.vk.directop.memoriesjournal.echo_edit.EchoEditScreen
import com.vk.directop.memoriesjournal.echo_list.EchoListAction
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
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Route.EchoList,
            ) {
                composable<Route.EchoList> {
                    MemoriesJournalTheme {
                        val viewModel = koinViewModel<EchoListScreenViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        EchoListScreen(
                            state = state,
                            onAction = { action ->
                                when (action) {
                                    is EchoListAction.OnOpenEchoEdit -> {
                                        navController.navigate(Route.EchoEdit)
                                    }

                                    else -> {}
                                }
                                viewModel.onAction(action)
                            },
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                        )
                    }
                }
                composable<Route.EchoEdit> {
                    EchoEditScreen()
                }
            }

        }
    }
}

