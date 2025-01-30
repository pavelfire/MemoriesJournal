package com.vk.directop.memoriesjournal.core.data

import java.io.File

class AudioUseCase(private val repository: AudioRepository) {

    fun startRecording(): File = repository.startRecording()

    fun stopRecording() = repository.stopRecording()

    fun pauseRecording() = repository.pauseRecording()

    fun resumeRecording() = repository.resumeRecording()

    suspend fun saveRecord(record: EchoRecordEntity) = repository.saveRecord(record)

    suspend fun getAllRecords() = repository.getAllRecords()

    fun startPlaying(
        file: File,
        onCompletion: () -> Unit,
        onProgress: (Long, Long) -> Unit
    ) = repository.startPlaying(file, onCompletion, onProgress)

    fun stopPlaying() = repository.stopPlaying()
}