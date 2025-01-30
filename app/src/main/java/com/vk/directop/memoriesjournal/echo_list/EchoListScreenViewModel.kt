package com.vk.directop.memoriesjournal.echo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.directop.memoriesjournal.core.data.AudioUseCase
import com.vk.directop.memoriesjournal.core.data.EchoRecordEntity
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

    private var isRecording = false
    private var isPaused = false
    private var currentFile: File? = null

    fun onAction(action: EchoListAction) {
        when (action) {
            is EchoListAction.OnItemClick -> {}
            is EchoListAction.OnSaveRecord -> saveRecording(action.description)
            EchoListAction.OnStartRecord -> startRecording()
            EchoListAction.OnStopRecord -> stopRecording()
            is EchoListAction.OnPlayClick -> startPlaying(action.record)
        }
    }

    private fun startRecording() {
        currentFile = useCase.startRecording()
        isRecording = true
        isPaused = false
    }

    private fun stopRecording() {
        useCase.stopRecording()
        isRecording = false
        isPaused = false
    }

    fun pauseRecording() {
        if (isRecording && !isPaused) {
            useCase.pauseRecording()
            isPaused = true
        }
    }

    fun resumeRecording() {
        if (isRecording && isPaused) {
            useCase.resumeRecording()
            isPaused = false
        }
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