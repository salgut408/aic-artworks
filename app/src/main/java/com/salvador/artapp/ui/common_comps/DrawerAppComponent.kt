package com.salvador.artapp.ui.common_comps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ModalDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.salvador.artapp.ui.navigation.NavigationScreens




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerAppComponent() {
    val  drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val currentScreen = remember { mutableStateOf(NavigationScreens.HomeScreen) }
    val coroutineScope = rememberCoroutineScope()
    ModalDrawer(drawerContent = {

    }) {

    }
}