package com.vk.directop.memoriesjournal.echo_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.directop.memoriesjournal.core.data.AudioUseCase
import com.vk.directop.memoriesjournal.core.data.EchoRecordEntity
import com.vk.directop.memoriesjournal.echo_list.models.Mood
import com.vk.directop.memoriesjournal.echo_list.models.toItemListState
import com.vk.directop.memoriesjournal.ui.theme.OnDarkBackground
import com.vk.directop.memoriesjournal.ui.theme.OnLightBackground
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID

class EchoListScreenViewModel(
    private val useCase: AudioUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EchoListState())
    val state = _state
        .onStart { loadRecords() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            EchoListState()
        )

    private var currentFile: File? = null

    fun onAction(action: EchoListAction) {
        when (action) {
            is EchoListAction.OnItemClick -> {}
            is EchoListAction.OnSaveRecord -> saveRecording(action.description)
            EchoListAction.OnStartRecord -> startRecording()
            EchoListAction.OnStopRecord -> stopRecording()
            is EchoListAction.OnPlayClick -> startPlaying(action.filePath)
        }
    }

    private fun startRecording() {
        currentFile = useCase.startRecording()
    }

    private fun stopRecording() {
        useCase.stopRecording()
    }

    private fun startPlaying(filePath: String) {
        useCase.startPlaying(File(filePath))
        Log.d("ThemeDebug", "Light surface: $OnLightBackground, Dark surface: $OnDarkBackground")

    }

    private fun stopPlaying() {
        useCase.stopPlaying()
    }

    private fun saveRecording(description: String) {
        currentFile?.let { file ->
            val record = EchoRecordEntity(
                id = UUID.randomUUID().toString(),
                description = description,
                filePath = file.absolutePath,
                createdAt = System.currentTimeMillis(),
                mood = Mood.entries.toTypedArray().random(),
                tags = listOf("example", "work", "day_time")
            )
            viewModelScope.launch {
                useCase.saveRecord(record)
                _state.update {
                    it.copy(
                        records = useCase.getAllRecords().map { entity ->
                            entity.toItemListState()
                        }
                    )
                }
            }

        }
    }

    private fun loadRecords() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    records = useCase.getAllRecords().map { entity ->
                        entity.toItemListState()
                    }
                )
            }
        }
    }
}