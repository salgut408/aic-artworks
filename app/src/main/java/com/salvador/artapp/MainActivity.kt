package com.salvador.artapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode.Companion.Points
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.salvador.artapp.ui.application.ArtApp
import com.salvador.artapp.ui.screens.home_screen.HomeScreen
import com.salvador.artapp.ui.theme.AICArtworksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AICArtworksTheme {
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(Color.LightGray)
                }
                ArtApp()
            }
        }
    }
}
