package com.salvador.artapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.salvador.artapp.ui.application.ArtAppState
import com.salvador.artapp.ui.screens.artwork_detail.DetailScreen
import com.salvador.artapp.ui.screens.exhibits.ExhibitionScreen
import com.salvador.artapp.ui.screens.home_screen.HomeScreen
import com.salvador.artapp.ui.screens.search.SearchScreen

@Composable
fun Navigation(
    appState: ArtAppState,
    padding: PaddingValues,
) {
    NavHost(
        navController = appState.navController,
        startDestination = NavigationScreens.HomeScreen.route,
        modifier = Modifier.padding(padding)

    ) {
        composable(
            route = NavigationScreens.HomeScreen.route
        ) {
            val id = it.arguments?.getString("id")
            HomeScreen(
                navController = appState.navController,
//                onArtworkClick = {
//                    appState.navController.navigate(
//                        route = NavigationScreens.DetailScreen.route
//                    )
//                }
            )
        }
        composable(
            route = NavigationScreens.DetailScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                },
            )
        ) {
            entry ->
            val id = entry.arguments?.getString("id")!!
            DetailScreen(id = id, )
        }
        composable(
            route = NavigationScreens.SearchScreen.route
        ) {
            SearchScreen(navController = appState.navController)
        }
        composable(
            route = NavigationScreens.ExhibitsScreen.route
        ){
            ExhibitionScreen(navController = appState.navController)
        }
    }

}