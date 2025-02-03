package com.vk.directop.memoriesjournal.echo_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.directop.memoriesjournal.core.data.AudioUseCase
import com.vk.directop.memoriesjournal.core.data.EchoRecordEntity
import com.vk.directop.memoriesjournal.core.navigation.Destination
import com.vk.directop.memoriesjournal.core.navigation.Navigator
import com.vk.directop.memoriesjournal.core.presentation.util.formatTime
import com.vk.directop.memoriesjournal.echo_list.models.ItemUi
import com.vk.directop.memoriesjournal.echo_list.models.Mood
import com.vk.directop.memoriesjournal.echo_list.models.toItemListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID

class EchoListScreenViewModel(
    private val useCase: AudioUseCase,
    private val navigator: Navigator
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
    private var isRecordingCompleted = false

    fun onAction(action: EchoListAction) {
        when (action) {
            is EchoListAction.OnItemClick -> {}
            EchoListAction.OnStartRecord -> toggleRecording()
            EchoListAction.OnPauseRecord -> togglePauseRecording()
            is EchoListAction.OnPlayClick -> startPlaying(action.record)
            EchoListAction.OnCloseBottomSheet -> closeBottomSheet()
            EchoListAction.OnNavigateUp -> onNavigateUp()
        }
    }

    private fun onNavigateUp() {
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }

    private fun toggleRecording() {
        if (isRecordingCompleted) {
            return
        }
        when {
            state.value.isRecording && state.value.isPaused -> resumeRecording()
            state.value.isRecording -> stopRecording()
            else -> startRecording()
        }
    }

    private fun startRecording() {
        currentFile = useCase.startRecording()
        _state.update { echoListState ->
            echoListState.copy(
                isPaused = false,
                isRecording = true
            )
        }
    }

    private fun stopRecording() {
        isRecordingCompleted = true
        useCase.stopRecording()
        _state.update { echoListState ->
            echoListState.copy(
                isPaused = false,
                isRecording = false
            )
        }
        saveRecording("Oh marathon")
    }

    private fun togglePauseRecording() {
        when {
            !state.value.isPaused && !state.value.isRecording -> closeBottomSheet()
            state.value.isRecording && state.value.isPaused -> {
                closeBottomSheet()
                stopRecording()
            }

            state.value.isRecording && !state.value.isPaused -> pauseRecording()
        }
    }

    private fun pauseRecording() {
        if (state.value.isRecording && !state.value.isPaused) {
            useCase.pauseRecording()
            _state.update { echoListState ->
                echoListState.copy(
                    isPaused = true,
                    isRecording = true
                )
            }
        }
    }

    private fun resumeRecording() {
        if (state.value.isRecording && state.value.isPaused) {
            useCase.resumeRecording()
            _state.update { echoListState ->
                echoListState.copy(
                    isPaused = false,
                    isRecording = true
                )
            }
        }
    }

    private fun closeBottomSheet() {
        isRecordingCompleted = false
    }

    private fun startPlaying(item: ItemUi) {
        val currentState = _state.value
        val currentlyPlaying = currentState.records.find { it.isPlaying }

        if (currentlyPlaying?.filePath == item.filePath) {
            useCase.stopPlaying()
            _state.update { echoListState ->
                echoListState.copy(
                    records = echoListState.records.map {
                        if (it.filePath == item.filePath) it.copy(isPlaying = false) else it
                    }
                )
            }
            return
        }

        currentlyPlaying?.let {
            useCase.stopPlaying()
            _state.update { echoListState ->
                echoListState.copy(
                    records = echoListState.records.map { it.copy(isPlaying = false) }
                )
            }
        }

        _state.update { echoListState ->
            echoListState.copy(
                records = echoListState.records.map {
                    if (it.filePath == item.filePath) it.copy(isPlaying = true) else it
                }
            )
        }

        useCase.startPlaying(
            File(item.filePath),
            onCompletion = {
                _state.update { echoListState ->
                    echoListState.copy(
                        records = echoListState.records.map {
                            if (it.filePath == item.filePath) it.copy(isPlaying = false) else it
                        }
                    )
                }
            },
            onProgress = { currentTime, totalTime ->
                _state.update { echoListState ->
                    echoListState.copy(
                        records = echoListState.records.map {
                            if (it.filePath == item.filePath) it.copy(
                                currentTime = formatTime(currentTime),
                                totalTime = formatTime(totalTime)
                            ) else it
                        }
                    )
                }
            }
        )
    }

    private fun saveRecording(description: String) {
        isRecordingCompleted = false
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
//                _state.update {
//                    it.copy(
//                        records = useCase.getAllRecords().map { entity ->
//                            entity.toItemListState()
//                        }
//                    )
//                }
                navigator.navigate(
                    destination = Destination.EchoEdit(record.id),
                )
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