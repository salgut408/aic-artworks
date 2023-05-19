package com.salvador.artapp.ui.screens.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salvador.artapp.domain.domain_models.ArtworkModel
import com.salvador.artapp.ui.common_comps.ArtScaffold
import com.salvador.artapp.ui.common_comps.ArtSurface
import com.salvador.artapp.ui.common_comps.BasicImage
import com.salvador.artapp.ui.common_comps.DefaultCard
import com.salvador.artapp.utils.Constants.Companion.QUERY_PAGE_SIZE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
//    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
) {

    val uiState by homeScreenViewModel.listUiState.collectAsState()
    val artworks = uiState.currentList
    val pagination = uiState.pagination
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    ArtScaffold(
        topBar = {
        HomeToolbar(
            title = "ART",
            scrollBehavior = scrollBehavior
        )},
        content = { padding ->

            Column(modifier = Modifier.fillMaxWidth()) {
                if (artworks.isNotEmpty()) {

                    ArtworkColumn(art = artworks, modifier = Modifier, paddingValues = padding)

                }
            }
        }
    )




}

@Composable
fun ArtworkColumn(
    art: List<ArtworkModel>,
//    onArtClick: ()-> Unit,
//    navController: NavController,
    paddingValues: PaddingValues,
    modifier: Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = paddingValues
    ) {
        items(art) { artwork ->
            ArtworkCard(
                artwork = artwork,
                onArtworkClick = { /*TODO*/ },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ArtworkCard(
    artwork: ArtworkModel,
    onArtworkClick: () -> Unit,
    modifier: Modifier,

    ) {
    DefaultCard(
        modifier = modifier.padding(8.dp),
        content = {
            ArtworkItem(
                artwork = artwork,
                onArtworkClick = { /*TODO*/ },
                modifier = modifier
            )

        }
    )

}

@Composable
fun ArtworkItem(
    artwork: ArtworkModel,
    onArtworkClick: () -> Unit,
    modifier: Modifier,
//    navController: NavController
) {
    ArtSurface(
        shape = MaterialTheme.shapes.medium,
        color = Color.Transparent,
    ) {
        Box(
            modifier = modifier
                .clickable { }
                .padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = artwork.title, style = MaterialTheme.typography.displaySmall, maxLines = 2, overflow = TextOverflow.Ellipsis)
                BasicImage(
                    modifier = modifier,
                    imgUrl = artwork.getOtherImgUrl(),
                    contentDescription = artwork.title,
                    elevation = 1.dp,
                    backgroundColor = Color.White,
                    borderWidth = 1.dp,
                    shape = RectangleShape,
                    borderColor = Color.LightGray
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = artwork.artistDisplay, style = MaterialTheme.typography.bodyMedium, )
                    Text(text = "Medium: ${artwork.mediumDisplay}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Color Score: ${artwork.colorfullness.toString()}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = artwork.styleTitle, style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Provenance: ${artwork.provenanceText}", style = MaterialTheme.typography.bodyMedium)

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolbar(
    title: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, fontWeight = FontWeight.Bold) },
        navigationIcon = {

        },
        scrollBehavior = scrollBehavior,
    )
}