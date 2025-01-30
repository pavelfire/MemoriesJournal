package com.vk.directop.memoriesjournal.core.domain.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file: File, onCompletion: () -> Unit, onProgress: (Int, Int) -> Unit)
    fun stop()
    fun getCurrentPosition(): Int
    fun getDuration(): Int
}