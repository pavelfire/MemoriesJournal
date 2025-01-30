package com.vk.directop.memoriesjournal.core.data.playback

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import com.vk.directop.memoriesjournal.core.domain.playback.AudioPlayer
import java.io.File

class AndroidAudioPlayer(
    private val context: Context
) : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playFile(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            start()
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}