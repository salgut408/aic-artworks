package com.salvador.artapp.ui.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.salvador.artapp.domain.domain_models.exhibit.ExhibitModel
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.ui.common_comps.*
import com.salvador.artapp.ui.navigation.NavigationScreens
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
) {
//    TODO Sharing intents
    val uiState by homeScreenViewModel.listUiState.collectAsStateWithLifecycle()
    val artworks = uiState.currentList
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val exhibits = homeScreenViewModel.exhibits.collectAsStateWithLifecycle()
    val ex = exhibits.value
    val getAllImages = homeScreenViewModel.getAllImages.collectAsLazyPagingItems()

    ArtScaffold(
        topBar = {
            HomeToolbar(
                title = "ART",
                scrollBehavior = scrollBehavior
            )
        },
        content = { padding ->
            Column(modifier = Modifier.fillMaxWidth()) {

//                SimpleFlowRow(list = ex)

                ExhibitionsRow(list = ex, contentPaddingValues = padding, modifier = Modifier)

                if (artworks.isNotEmpty()) {
                    ArtworkList(
                        artworks = homeScreenViewModel.getAllImages,
                        contentPaddingValues = padding,
                        onArtworkClick = { },
                        navController = navController
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SimpleFlowRow(list: List<ExhibitModel>) {
    FlowRow(
        Modifier
            .fillMaxWidth(1f)
            .wrapContentHeight(align = Alignment.Top),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        maxItemsInEachRow = 5
    ) {
        list.map {
            Box(
                Modifier
                    .align(Alignment.CenterVertically)
                    .width(90.dp)
                    .height(90.dp)
                    .background(Color.Green)
            ) {
                Text(text = it.title.toString(),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(3.dp))
                BasicImage(imgUrl = it.imageUrl ?: "",
                    contentDescription = "",
                    elevation = 0.dp,
                    backgroundColor = Color.Transparent,
                    borderWidth = 0.dp,
                    borderColor = Color.Transparent)
            }
        }
    }
}

@Composable
fun ExhibitionsRow(
    list: List<ExhibitModel>,
    contentPaddingValues: PaddingValues,
    modifier: Modifier,
) {
//    Column(modifier = modifier.fillMaxWidth()) {

    LazyRow(
        contentPadding = contentPaddingValues,
    ) {
        items(list) { exhibit ->
            Card(modifier
//                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
//                        horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.padding(16.dp)
                ) {

                    Text(text = exhibit.title ?: "",
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.width(180.dp))

                    BasicImage(
                        imgUrl = exhibit.imageUrl ?: "0",
                        contentDescription = "",
                        elevation = 0.dp,
                        backgroundColor = Color.Transparent,
                        borderWidth = 0.dp,
                        borderColor = Color.Transparent,
                        shape = RoundedCornerShape(8.dp),
                        modifier = modifier
                            .wrapContentSize()
                            .size(100.dp)
                    )
                }

//                    HtmlText(html = exhibit.shortDescription ?: "", modifier = modifier.padding(start = 16.dp))
            }
            Spacer(modifier = modifier.width(8.dp))
        }
    }

}


//TODO Fix scrolling to top after config change
@Composable
fun ArtworkList(
    artworks: Flow<PagingData<ArtworkModel>>,
    contentPaddingValues: PaddingValues,
    onArtworkClick: (String) -> Unit,
    navController: NavController,
) {
    val lazyArtItems = artworks.collectAsLazyPagingItems()
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxWidth()) {

    }


    LazyColumn(
        contentPadding = contentPaddingValues,
//        state = scrollState
    ) {
        items(lazyArtItems) { art ->
            ArtworkCard(
                artwork = art!!,
                onArtworkClick = { },
                modifier = Modifier,
                navController = navController
            )
        }
        lazyArtItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
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
fun ArtworkCard(
    artwork: ArtworkModel,
    onArtworkClick: (String) -> Unit,
    modifier: Modifier,
    navController: NavController,

    ) {
    DefaultCard(
        modifier = modifier.padding(8.dp),
        content = {
            ArtworkItem(
                artwork = artwork,
                onArtworkClick = { onArtworkClick(artwork.id.toString()) },
                modifier = modifier,
                navController = navController
            )

        }
    )

}

@Composable
fun ArtworkItem(
    artwork: ArtworkModel,
    onArtworkClick: () -> Unit,
    modifier: Modifier,
    navController: NavController,
) {
    ArtSurface(
        shape = MaterialTheme.shapes.medium,
        color = Color.Transparent,
    ) {
        Box(
            modifier = modifier
                .clickable {
                    navController.navigate(
                        NavigationScreens.DetailScreen.withArgs(
                            artwork.id
                        )
                    ){
                        // ???
                    }
                }
                .padding(8.dp)

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(text = artwork.title,
                    style = MaterialTheme.typography.displaySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
                BasicImage(
                    modifier = modifier,
                    imgUrl = artwork.getOtherImgUrl(),
                    contentDescription = artwork.title,
                    elevation = 1.dp,
                    backgroundColor = Color.Transparent,
                    borderWidth = 0.dp,
                    shape = RectangleShape,
                    borderColor = Color.Transparent
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(text = artwork.artistDisplay,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold)
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
        navigationIcon = { /* TODO make searching here  */},
        scrollBehavior = scrollBehavior,
    )
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
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
    onClickRetry: () -> Unit,
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

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},

    ) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = Modifier.fillMaxWidth()) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.DarkGray),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(8.dp)
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<NavigationScreens>,
) {
    BottomNavigation() {
//        val currentRoute = cu
    }

}

@Composable
fun SearchBar2(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "Search...") }
    Box(modifier = modifier) {
        BasicTextField(value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)

            )
        }
    }

}













