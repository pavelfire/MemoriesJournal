package com.vk.directop.memoriesjournal.core.domain.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}