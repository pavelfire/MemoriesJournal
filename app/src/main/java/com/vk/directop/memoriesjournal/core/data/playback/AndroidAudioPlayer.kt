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

    override fun playFile(file: File, onCompletion: () -> Unit, onProgress: (Long, Long) -> Unit) {
        stop()

        player = MediaPlayer().apply {
            setDataSource(context, file.toUri())
            prepare()
            start()

            setOnCompletionListener {
                onCompletion()
                stop()
            }

            setOnPreparedListener {
                onProgress(0, duration.toLong())
            }

            setOnBufferingUpdateListener { _, percent ->
                onProgress(currentPosition.toLong(), duration.toLong())
            }
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }

    override fun getCurrentPosition(): Int = player?.currentPosition ?: 0
    override fun getDuration(): Int = player?.duration ?: 0
}