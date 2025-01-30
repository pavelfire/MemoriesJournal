package com.vk.directop.memoriesjournal.core.data.recorder

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import com.vk.directop.memoriesjournal.core.domain.recorder.AudioRecorder
import java.io.File
import java.io.FileOutputStream

class AndroidAudioRecorder(
    private val context: Context
) : AudioRecorder {

    private var recorder: MediaRecorder? = null
    private var isRecording = false
    private var isPaused = false

    private fun createRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }
    }

    override fun start(outputFile: File) {
        if (isRecording) return

        createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(outputFile).fd)

            prepare()
            start()

            recorder = this
            isRecording = true
            isPaused = false
        }
    }

    override fun stop() {
        recorder?.let {
            if (isRecording) {
                it.stop()
                it.reset()
                isRecording = false
                isPaused = false
            }
        }
    }

    fun pause() {
        if (isRecording && !isPaused) {
            recorder?.pause()
            isPaused = true
        } else {
            Log.w("AndroidAudioRecorder", "recording not started.")
        }
    }

    fun resume() {
        if (isPaused) {
            recorder?.resume()
            isPaused = false
        } else {
            Log.w("AndroidAudioRecorder", "recording not paused.")
        }
    }
}