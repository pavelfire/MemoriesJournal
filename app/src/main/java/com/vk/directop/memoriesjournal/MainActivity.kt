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
import androidx.navigation.toRoute
import com.vk.directop.memoriesjournal.core.navigation.Destination
import com.vk.directop.memoriesjournal.core.navigation.NavigationAction
import com.vk.directop.memoriesjournal.core.navigation.Navigator
import com.vk.directop.memoriesjournal.core.presentation.util.ObserveAsEvents
import com.vk.directop.memoriesjournal.echo_edit.EchoEditScreen
import com.vk.directop.memoriesjournal.echo_list.EchoListScreen
import com.vk.directop.memoriesjournal.echo_list.EchoListScreenViewModel
import com.vk.directop.memoriesjournal.ui.theme.MemoriesJournalTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

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
            val navigator = koinInject<Navigator>()

            ObserveAsEvents(flow = navigator.navigationActions) { action ->
                when (action) {
                    is NavigationAction.Navigate -> navController.navigate(
                        action.destination
                    ) {
                        action.navOptions(this)
                    }

                    NavigationAction.NavigateUp -> navController.navigateUp()
                }
            }
            NavHost(
                navController = navController,
                startDestination = Destination.EchoList,
            ) {
                composable<Destination.EchoList> {
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
                composable<Destination.EchoEdit> {
                    val args = it.toRoute<Destination.EchoEdit>()
                    EchoEditScreen(
                        id = args.id
                    )
                }
            }
        }
    }
}

