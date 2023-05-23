package com.salvador.artapp.ui.screens.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.ui.common_comps.ArtScaffold
import com.salvador.artapp.ui.common_comps.ArtSurface
import com.salvador.artapp.ui.common_comps.BasicImage
import com.salvador.artapp.ui.common_comps.DefaultCard
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
) {

    val uiState by homeScreenViewModel.listUiState.collectAsStateWithLifecycle()
    val artworks = uiState.currentList
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()





    ArtScaffold(
        topBar = {
        HomeToolbar(
            title = "ART",
            scrollBehavior = scrollBehavior
        ) },
        content = { padding ->
            Column(modifier = Modifier.fillMaxWidth()) {
                if (artworks.isNotEmpty()) {
//                    ArtworkColumn(art = artworks, modifier = Modifier, paddingValues = padding)
                    ArtworkList(artworks = homeScreenViewModel.art, contentPaddingValues = padding)
                }
            }
        }
    )
}

//TODO Fix scrolling to top after config change
@Composable
fun ArtworkList(artworks: Flow<PagingData<ArtworkModel>>, contentPaddingValues: PaddingValues) {
    val lazyArtItems = artworks.collectAsLazyPagingItems()
    val scrollState = rememberLazyListState()
    LazyColumn (
        contentPadding = contentPaddingValues,
        state = scrollState
    ) {
        items(lazyArtItems) { art ->
            ArtworkCard(
                artwork =art!! ,
                onArtworkClick = { /*TODO*/ },
                modifier = Modifier)
        }
        lazyArtItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize())}
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyArtItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyArtItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
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
                    horizontalAlignment = Alignment.Start,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(text = artwork.artistDisplay, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                    Text(text = artwork.styleTitle, style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Medium: ${artwork.mediumDisplay}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Color Score: ${artwork.colorfullness.toString()}", style = MaterialTheme.typography.bodyMedium)

//                    TODO Fix Color ?
//                    Text(text = artwork.color.toString(), style = MaterialTheme.typography.bodyMedium)
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

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.displaySmall,
            color = Color.Red
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "Try again")
        }
    }
}