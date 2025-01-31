package com.vk.directop.memoriesjournal.echo_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vk.directop.memoriesjournal.core.presentation.components.PlayPanel
import com.vk.directop.memoriesjournal.echo_list.EchoListAction
import com.vk.directop.memoriesjournal.echo_list.models.ItemUi
import com.vk.directop.memoriesjournal.echo_list.models.Mood
import com.vk.directop.memoriesjournal.ui.theme.InterFontFamily

@Composable
fun EchoItem(
    item: ItemUi,
    onAction: (EchoListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = item.mood.toDrawableRes()),
            contentDescription = "mood",
            modifier = Modifier.padding(top = 8.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.onSecondary)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = InterFontFamily,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onAction(EchoListAction.OnItemClick(item))
                        }
                )
                Text(
                    text = item.createdAt,
                    fontSize = 12.sp,
                    fontFamily = InterFontFamily,
                )
            }
            PlayPanel(
                isPlaying = item.isPlaying,
                currentTime = item.currentTime,
                totalTime = item.totalTime,
                progress = item.progress,
                onPlayPauseClick = { onAction(EchoListAction.OnPlayClick(item)) }
            )
            TagList(item.tags)
        }
    }
}

//@Preview(showBackground = true, backgroundColor = 0xFF998855)
@PreviewLightDark
@Composable
private fun EchoListItemPreview() {
    MaterialTheme {
        EchoItem(
            item = ItemUi(
                id = "sdfdf",
                description = "My Entry 1",
                mood = Mood.EXCITED,
                createdAt = "12/03/25 15:20",
                tags = listOf("sds", "dsfdfg"),
                filePath = "path of file"
            ),
            onAction = {}
        )
    }
}
