package com.salvador.artapp.ui.screens.artwork_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
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
    var mutedColor by remember { mutableStateOf(defaultDomColor) }
    var textColor by remember { mutableStateOf(defaultDomColor) }


    val uiState by detailViewModel.detailUiState.collectAsStateWithLifecycle()
    val artwork = uiState.art.artData!!
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val pic = artwork.getOtherImgUrl()
    pic.let {
        detailViewModel.fetchColors(pic, LocalContext.current) { color ->
            mutedColor = color
        }
    }

    ArtScaffold(
        topBar = {
            DetailToolbar(
                title = artwork.title ?: "",
                modifier = modifier.background(mutedColor),
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //OnClick Method
                },
                containerColor = mutedColor,
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        },

        content = { paddingValues ->
            DetailContent(
                artwork = artwork,
                modifier = modifier,
                dominantColor = mutedColor,
                contenPaddingValues = paddingValues
            )
        }
    )


}

@Composable
fun DetailContent(
    artwork: ArtDetails,
    modifier: Modifier,
    dominantColor: Color,
    contenPaddingValues: PaddingValues,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(artwork.getOtherImgUrl()).crossfade(false).build(),
            modifier = Modifier.fillMaxSize().blur(8.dp).alpha(0.9f),
            contentScale = ContentScale.Crop,
            contentDescription = artwork.title
        )


        Column(
            modifier = modifier
                .padding(16.dp)
//                .fillMaxSize()
                .padding(contenPaddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                BasicImage(
                    modifier = modifier
                        .size(300.dp)
                        .fillMaxWidth(),
                    imgUrl = artwork.getOtherImgUrl(),
                    contentDescription = artwork.title,
                    elevation = 0.dp,
                    backgroundColor = dominantColor,
                    borderWidth = 10.dp,
                    borderColor = dominantColor,
                    shape = RectangleShape,
                )




            ArtInfoCollumn(modifier = modifier, artwork = artwork)


        }
    }
}

@Composable
fun ArtInfoCollumn(modifier: Modifier, artwork: ArtDetails) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        Text(text = artwork.artistDisplay,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp)
        Text(text = artwork.mediumDisplay, style = MaterialTheme.typography.bodyMedium)
        Text(text = artwork.dimensions, style = MaterialTheme.typography.bodyMedium)
        Text(text = "Style: ${artwork.departmentTitle}",
            style = MaterialTheme.typography.bodyMedium)

        Text(text = "Is on view: ${artwork.isOnView.toString()}",
            style = MaterialTheme.typography.bodyMedium)
        Text(text = "AIC Department: ${artwork.departmentTitle}",
            style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = modifier.height(8.dp))
        ThemesList(list = artwork.themeTitles, modifier = modifier)
    }
}


@Composable
fun ThemesList(list: List<String>, modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Themes", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        list.map { theme ->
            Text(text = "- ${theme}", modifier = modifier.padding(start = 6.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailToolbar(
    title: String,
    modifier: Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, fontWeight = FontWeight.Bold) },
        navigationIcon = {},
        scrollBehavior = scrollBehavior,
    )

}