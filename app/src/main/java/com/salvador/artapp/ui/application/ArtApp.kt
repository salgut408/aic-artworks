package com.salvador.artapp.ui.application

import android.content.res.Resources
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salvador.artapp.ui.common_comps.ArtScaffold
import com.salvador.artapp.ui.navigation.Navigation
import com.salvador.artapp.ui.navigation.NavigationScreens
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtApp(
    modifier: Modifier = Modifier,
) {
    val appState = rememberAppState()

    val navItems = listOf(NavigationScreens.HomeScreen, NavigationScreens.SearchScreen, NavigationScreens.FavoritesScreen)

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navStackEntry by appState.navController.currentBackStackEntryAsState()
                val currentDestination = navStackEntry?.destination
                navItems.forEach{ screen ->
                    currentDestination?.hierarchy?.any {it.route == screen.route}?.let {
                        BottomNavigationItem(
                            icon = {Icon(screen.icon, contentDescription = null)},
                            label = { Text(text = screen.name)},
                            selected = it,
                            alwaysShowLabel = false,
                            onClick = {
                                appState.navController.navigate(screen.route) {
                                    popUpTo(appState.navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Navigation(appState = appState, padding = innerPadding)

    }
}



@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
) =
    remember(navController, ) {
        ArtAppState(navController, )
    }