package com.salvador.artapp.ui.common_comps

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import java.io.FileDescriptor

@Composable
fun BasicImage(
    imgUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp,
    backgroundColor: Color,
    borderWidth: Dp,
    borderColor: Color,
    shape: Shape = CircleShape,
    padding: Dp = 0.dp
) {
    ArtSurface(
        color = backgroundColor,
        elevation = elevation,
        shape = shape,
        modifier = modifier,
        border = BorderStroke(borderWidth, borderColor),
        padding = padding,
        content = {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imgUrl).crossfade(true).build(),
                contentDescription = contentDescription,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    )

}