package com.vk.directop.memoriesjournal.echo_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.vk.directop.memoriesjournal.R
import com.vk.directop.memoriesjournal.echo_list.EchoListScreen
import com.vk.directop.memoriesjournal.echo_list.EchoListState
import com.vk.directop.memoriesjournal.echo_list.recordsPreview
import com.vk.directop.memoriesjournal.ui.theme.DarkBackground
import com.vk.directop.memoriesjournal.ui.theme.LightBackground

@Composable
fun EmptyList(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LightBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.no_entries),
            contentDescription = "mood",
            modifier = Modifier

        )
        Text(
            text = "No Entries",
            modifier = Modifier
        )
        Text(
            text = "Start recording your first Echo",
            modifier = Modifier
        )
    }
}

@PreviewLightDark
@Composable
private fun EmptyListPreview() {
    MaterialTheme {
        EmptyList()
    }
}