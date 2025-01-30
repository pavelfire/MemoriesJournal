package com.vk.directop.memoriesjournal.core.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormattedDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault())
    return formatter.format(Date(this))
}

fun formatTime(milliseconds: Long): String {
    val totalSeconds = milliseconds / 1000
    val minutes = totalSeconds / 60
    val sec = totalSeconds % 60
    return String.format(Locale.US, "%d:%02d", minutes, sec)
}