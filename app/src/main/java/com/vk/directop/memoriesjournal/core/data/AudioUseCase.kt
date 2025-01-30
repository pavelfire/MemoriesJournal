package com.vk.directop.memoriesjournal.core.data

import java.io.File

class AudioUseCase(private val repository: AudioRepository) {
    fun startRecording(): File = repository.startRecording()
    fun stopRecording() = repository.stopRecording()
    suspend fun saveRecord(record: EchoRecordEntity) = repository.saveRecord(record)
    suspend fun getAllRecords() = repository.getAllRecords()
    fun startPlaying(file: File) = repository.startPlaying(file)
    fun stopPlaying() = repository.stopPlaying()
}