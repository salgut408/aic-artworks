package com.salvador.artapp.ui.screens.exhibits

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.ExhibitModel
import com.salvador.artapp.ui.common_comps.ArtScaffold
import com.salvador.artapp.ui.common_comps.BasicImage
import com.salvador.artapp.ui.common_comps.HtmlText
import com.salvador.artapp.ui.screens.home_screen.HomeToolbar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExhibitionScreen(
    navController: NavController,
    exhibitsViewModel: ExhibitsViewModel = hiltViewModel(),
) {
    val exhibits = exhibitsViewModel.exhibits.collectAsLazyPagingItems()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()


    ArtScaffold(
        topBar = { HomeToolbar(title = "Sxhibits", scrollBehavior = scrollBehavior) },
        content = { padding ->
            Column(modifier = Modifier.fillMaxSize()) {
                ExhibitionsRow(
                    list = exhibits,
                    contentPaddingValues = padding,
                    modifier = Modifier
                )
            }
        }
    )

}

@Composable
fun ExhibitionsRow(
    list: LazyPagingItems<ExhibitModel>,
    contentPaddingValues: PaddingValues,
    modifier: Modifier,
) {
    val exhibs = list
    LazyColumn(
        contentPadding = contentPaddingValues,
    ) {
        items(exhibs) { exhibit ->
            ExhibitionItem(modifier = modifier, exhibit = exhibit ?: ExhibitModel())
            Spacer(modifier = modifier.height(4.dp))
        }
    }

}



@Composable
fun ExhibitionItem(
    modifier: Modifier,
    exhibit: ExhibitModel,
) {
    Card() {
        BasicImage(
            modifier = modifier.fillMaxWidth(),
            imgUrl = exhibit.imageUrl ?: "",
            contentDescription = exhibit.title,
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            borderWidth = 0.dp,
            borderColor = Color.Transparent,
            shape = RectangleShape
        )
        Text(
            text = exhibit.title ?: "",
            softWrap = true,
            modifier = modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
            fontWeight = FontWeight.Bold
        )
        HtmlText(
            html = exhibit.shortDescription ?: "",
            modifier = modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
            )

    }
}
