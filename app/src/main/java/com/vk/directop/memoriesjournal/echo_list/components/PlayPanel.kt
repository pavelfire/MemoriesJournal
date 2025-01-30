package com.vk.directop.memoriesjournal.echo_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlayPanel(
    isPlaying: Boolean,
    currentTime: String,
    totalTime: String,
    progress: Float,
    onPlayPauseClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(26.dp))
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { onPlayPauseClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    id = if (isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play
                ),
                contentDescription = "Play/Pause",
                tint = Color(0xFF1F70F5),
                modifier = Modifier.size(14.dp)
            )
        }
        Spacer(modifier = Modifier.width(6.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .weight(1f)
                .height(4.dp),
            color = Color(0xFF1F70F5),
            trackColor = Color(0xFFDDDDDD),
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "$currentTime/$totalTime",
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}

@Preview
@Composable
private fun PlayPanelDemo() {
    var isPlaying by remember { mutableStateOf(false) }
    PlayPanel(
        isPlaying = isPlaying,
        currentTime = "6:15",
        totalTime = "12:30",
        progress = 0.5f,
        onPlayPauseClick = { isPlaying = !isPlaying }
    )
}

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val sec = seconds % 60
    return String.format("%d:%02d", minutes, sec)
}