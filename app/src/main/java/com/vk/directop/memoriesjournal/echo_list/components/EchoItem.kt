package com.vk.directop.memoriesjournal.echo_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vk.directop.memoriesjournal.R
import com.vk.directop.memoriesjournal.echo_list.EchoListItemState
import com.vk.directop.memoriesjournal.ui.theme.InterFontFamily
import com.vk.directop.memoriesjournal.ui.theme.LightBackground

@Composable
fun EchoItem(
    item: EchoListItemState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp)
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
                .background(LightBackground)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontFamily = InterFontFamily,

                    )
                Text(
                    text = "17:30",
                    fontSize = 12.sp,
                    fontFamily = InterFontFamily,
                )
            }

            Text(
                text = "here record",
                fontSize = 12.sp,
                fontFamily = InterFontFamily,
            )
            Text(
                text = "#work",
                fontSize = 13.sp,
                fontFamily = InterFontFamily,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF998855)
@Composable
private fun EchoListItemPreview() {
    MaterialTheme {
        EchoItem(EchoListItemState())
    }
}
