package com.salvador.artapp.ui.navigation

sealed class NavigationScreens (val route: String) {
    object HomeScreen: NavigationScreens("home_screen")
    object DetailScreen: NavigationScreens("detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }

}