package com.salvador.artapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationScreens (val route: String, val icon: ImageVector, val name: String) {

    object HomeScreen: NavigationScreens("home_screen", Icons.Default.Home, "Home")
    object DetailScreen: NavigationScreens("detail_screen", Icons.Default.Menu, "Details")
    object SearchScreen: NavigationScreens("search_screen", Icons.Default.Search, "Search")
    object FavoritesScreen: NavigationScreens("favorites_screen", Icons.Default.Favorite, "Favs")


    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }

}