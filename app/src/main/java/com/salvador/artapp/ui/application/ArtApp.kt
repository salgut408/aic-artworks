package com.salvador.artapp.ui.application

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salvador.artapp.ui.navigation.Navigation
import com.salvador.artapp.ui.navigation.NavigationScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtApp(
    modifier: Modifier = Modifier,

) {
    val appState = rememberAppState()
    val navItems = listOf(NavigationScreens.HomeScreen, NavigationScreens.SearchScreen, NavigationScreens.FavoritesScreen, NavigationScreens.ExhibitsScreen)

    val selectedItem = remember { mutableStateOf(navItems[0]) }

    ModalNavigationDrawer(
        drawerState = appState.drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.LightGray,
                drawerContentColor = Color.Black,
                content = {
                    LazyColumn {
                        items(navItems) { item ->
                            NavigationDrawerItem(
                                label = { Text(text = item.name)  },
                                icon = { Icon(item.icon, contentDescription = null) },
                                selected = item == selectedItem.value,
                                onClick = {
                                    selectedItem.value = item
                                    appState.scope.launch { appState.drawerState.close() }

                                    appState.navController.navigate(selectedItem.value.route) {
                                        popUpTo(appState.navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.Transparent,
                                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                ),
                                modifier = modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }
                }
            )
        },
        content = {
            Scaffold(

                bottomBar = {
                    BottomNavigation {
                        val navStackEntry by appState.navController.currentBackStackEntryAsState()
                        val currentDestination = navStackEntry?.destination
                        navItems.forEach{ screen ->
                            currentDestination?.hierarchy?.any {it.route == screen.route}?.let {
                                BottomNavigationItem(
                                    icon = {Icon(screen.icon, contentDescription = null, tint = Color.White)},
                                    label = { Text(text = screen.name, color = Color.White)},
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
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) =
    remember(navController, drawerState, scope) {
        ArtAppState(navController, drawerState, scope)
    }