package com.salvador.artapp.ui.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.salvador.artapp.ui.common_comps.ArtScaffold
import com.salvador.artapp.ui.navigation.NavigationScreens
import com.salvador.artapp.ui.screens.home_screen.ArtworkCard
import com.salvador.artapp.ui.screens.home_screen.ArtworkList
import com.salvador.artapp.ui.screens.home_screen.SearchBar
import com.salvador.artapp.ui.screens.home_screen.SearchBar2

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel(),

    ) {
    val uiState by searchViewModel.searchUiState.collectAsStateWithLifecycle()
    val artworks = uiState.currentList


    ArtScaffold(
        topBar = { SearchBar3( onSearch = {}) },
        content = { padding ->
            Column(modifier = Modifier.fillMaxWidth()) {
                SearchBar3()

            }
            if (artworks.isNotEmpty()) {
                ArtworkList(
                    artworks = searchViewModel.art,
                    contentPaddingValues = padding ,
                    onArtworkClick = {},
                    navController = navController
                )
//                searchViewModel.searchUiState.value.currentList.map { art->
//                    ArtworkCard(artwork = art, onArtworkClick = {}, modifier = Modifier, navController = navController)
//                }

            }

        }
    )


}



@Composable
fun SearchBar3(
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





