package com.salvador.artapp.ui.application

import android.content.res.Resources
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salvador.artapp.ui.navigation.Navigation
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtApp(
    modifier: Modifier = Modifier,
) {
    val appState = rememberAppState()
    val backStackEntry by appState.navController.currentBackStackEntryAsState()

    Scaffold() { innerPadding ->
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