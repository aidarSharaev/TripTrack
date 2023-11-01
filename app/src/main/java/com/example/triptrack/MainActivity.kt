package com.example.triptrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.triptrack.presentation.navgraph.NavGraph
import com.example.triptrack.ui.theme.TripTrackTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  val viewModel by viewModels<MainViewModel>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    installSplashScreen().apply {
      setKeepOnScreenCondition {
        viewModel.splashCondition
      }
    }
    setContent {
      TripTrackTheme {
        val isSystemInDarkMode = isSystemInDarkTheme()
        val systemController = rememberSystemUiController()
        SideEffect {
          systemController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isSystemInDarkMode
          )
        }
        val startDestination = viewModel.startDestination
        NavGraph(startDestination = startDestination)
      }
    }
  }
}

//viewModel::onEvent
