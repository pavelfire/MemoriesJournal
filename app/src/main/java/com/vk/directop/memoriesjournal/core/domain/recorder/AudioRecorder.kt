package com.vk.directop.memoriesjournal.core.domain.recorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}