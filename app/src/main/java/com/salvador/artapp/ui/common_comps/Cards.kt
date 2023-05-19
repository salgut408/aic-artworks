package com.salvador.artapp.ui.common_comps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultCard(
    modifier: Modifier,
    color: Color = Color.LightGray,
    contentColor: Color = Color.Black,
    content: @Composable () -> Unit,
) {
    Card(
        backgroundColor = color,
        contentColor = contentColor,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp)
        ) {
            content()
        }
    }
}