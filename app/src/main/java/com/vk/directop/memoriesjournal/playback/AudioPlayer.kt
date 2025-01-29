package com.vk.directop.memoriesjournal.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}