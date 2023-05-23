package com.salvador.artapp.ui.screens.artwork_detail

import android.graphics.ColorSpace.Rgb
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.salvador.artapp.domain.domain_models.detail.ArtDetail
import com.salvador.artapp.domain.domain_models.detail.ArtDetails
import com.salvador.artapp.ui.common_comps.ArtScaffold
import com.salvador.artapp.ui.common_comps.BasicImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: String,
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel(),

    ) {

    detailViewModel.loadArtwork(id)
    val defaultDomColor = MaterialTheme.colorScheme.background
    var dominantColor by remember {
        mutableStateOf(defaultDomColor)
    }

    val uiState by detailViewModel.detailUiState.collectAsStateWithLifecycle()
    val artwork = uiState.art.artData!!
    val color = artwork.color
    Log.e("DETAILS_VM", artwork.toString())

    val pic = artwork.getOtherImgUrl()
    pic.let {
        detailViewModel.fetchColors(pic, LocalContext.current) { color ->
            dominantColor = color
        }
    }

    ArtScaffold(
        content = {
            DetailContent(
                artwork = artwork, modifier = modifier,
                dominantColor = dominantColor
            )
        }
    )


}

@Composable
fun DetailContent(
    artwork: ArtDetails,
    modifier: Modifier,
    dominantColor: Color,
) {
    Column(
        modifier = modifier
            .background(
                dominantColor
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = artwork.title ?: "0",
            style = MaterialTheme.typography.headlineLarge,
        )
        BasicImage(
            modifier = modifier.size(300.dp),
            imgUrl = artwork.getOtherImgUrl(),
            contentDescription = artwork.title,
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            borderWidth = 0.dp,
            borderColor = Color.Transparent,
            shape = RectangleShape,
        )

        Text(text = "Medium: ${artwork.mediumDisplay}", style = MaterialTheme.typography.bodyMedium)


        Text(
            text = artwork.color.toString() ?: "0",
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}