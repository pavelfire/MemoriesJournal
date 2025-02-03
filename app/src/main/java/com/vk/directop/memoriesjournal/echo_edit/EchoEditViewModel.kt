package com.vk.directop.memoriesjournal.echo_edit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.directop.memoriesjournal.core.navigation.Navigator
import kotlinx.coroutines.launch

class EchoEditViewModel(
    private val navigator: Navigator
): ViewModel() {

    fun onAction(action: EchoEditAction) {
        when (action) {
            EchoEditAction.OnNavigateUp -> onNavigateUp()
        }
    }

    private fun onNavigateUp() {
        Log.d("myTag", "navigate up")
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }
}