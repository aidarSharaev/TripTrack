package com.example.triptrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.triptrack.di.AppEntryUseCases
import com.example.triptrack.presentation.onboarding.OnBoardingViewModel
import com.example.triptrack.presentation.onboarding.component.OnBoardingScreen
import com.example.triptrack.ui.theme.TripTrackTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//  @Inject
//  lateinit var ReadAppEntry: AppEntryUseCases
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    installSplashScreen()

//    lifecycleScope.launch {
//      ReadAppEntry.readAppEntry().collect {
//        Log.d("AAAA", it.toString())
//      }
//    }

    setContent {
      TripTrackTheme {
        val viewModel: OnBoardingViewModel = hiltViewModel()
        OnBoardingScreen(
          event = viewModel::onEvent
        )
      }
    }
  }
}
