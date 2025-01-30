package com.vk.directop.memoriesjournal.core.data

import com.vk.directop.memoriesjournal.core.data.playback.AndroidAudioPlayer
import com.vk.directop.memoriesjournal.core.data.recorder.AndroidAudioRecorder
import java.io.File
import java.util.UUID

class AudioRepository(
    private val dao: EchoDao,
    private val recorder: AndroidAudioRecorder,
    private val player: AndroidAudioPlayer,
    private val cacheDir: File
) {
    private var currentFile: File? = null

    fun startRecording(): File {
        val file = File(cacheDir, "${UUID.randomUUID()}.mp3")

        file.parentFile?.let { parent ->
            if (!parent.exists()) parent.mkdirs()
        }
        recorder.start(file)
        currentFile = file
        return file
    }

    fun stopRecording() {
        recorder.stop()
    }

    suspend fun saveRecord(record: EchoRecordEntity) {
        dao.insertRecord(record)
    }

    suspend fun getAllRecords(): List<EchoRecordEntity> {
        return dao.getAllRecords()
    }

    fun startPlaying(file: File, onCompletion: () -> Unit, onProgress: (Int, Int) -> Unit) {
        player.playFile(file, onCompletion, onProgress)
    }

    fun stopPlaying() {
        player.stop()
    }
}