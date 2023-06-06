package com.salvador.artapp.ui.application

import androidx.compose.material.ScaffoldState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

class ArtAppState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val navController: NavHostController,
    val drawerState: DrawerState,
    val scope: CoroutineScope
) {
}