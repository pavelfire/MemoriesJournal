package com.vk.directop.memoriesjournal.recorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}