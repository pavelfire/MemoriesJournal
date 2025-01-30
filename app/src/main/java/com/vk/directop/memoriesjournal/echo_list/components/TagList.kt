package com.vk.directop.memoriesjournal.echo_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vk.directop.memoriesjournal.ui.theme.InterFontFamily
import com.vk.directop.memoriesjournal.ui.theme.TagBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagList(tags: List<String>) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        tags.forEach { tag ->
            TagItem(tag)
            Spacer(Modifier.width(6.dp))
        }
    }
}

@Composable
fun TagItem(tag: String) {
    Box(
        modifier = Modifier
            .padding(top = 6.dp)
            .background(TagBackground, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 6.dp, vertical = 6.dp)
    ) {
        Text(
            text = tag,
            fontFamily = InterFontFamily,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTagList() {
    TagList(listOf("#Family", "#Work", "#Conundrums", "#Mountains", "#Sea", "#Sun"))
}