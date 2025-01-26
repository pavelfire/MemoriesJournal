package com.vk.directop.memoriesjournal.echo_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vk.directop.memoriesjournal.R
import com.vk.directop.memoriesjournal.echo_list.components.EchoList
import com.vk.directop.memoriesjournal.ui.theme.DarkBackground

@Composable
fun EchoListScreen(
    state: EchoListState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Your EchoJournal"
        )
        if(state.records.isEmpty()){

        }else {
            EchoList(state.records)
        }
    }
}

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ){
        Text(
            text = "Android",
            modifier = Modifier
                .align(Alignment.Center)
        )
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.mood),
            contentDescription = "mood",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun EchoListScreenPreview() {
    MaterialTheme {
        EchoListScreen(
            state = EchoListState(
                recordsPreview
            )
        )
    }
}

val recordsPreview = listOf("one", "two", "three", "four")