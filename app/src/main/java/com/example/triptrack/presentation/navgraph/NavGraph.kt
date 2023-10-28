package com.example.triptrack.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.triptrack.presentation.component.OnBoardingScreen
import com.example.triptrack.presentation.onboarding.OnBoardingViewModel
import com.example.triptrack.presentation.order_screen.NewOrderScreen

@Composable
fun NavGraph(
  startDestination: String
) {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = startDestination) {
    navigation(
      route = Route.AppStartNavigation.route,
      startDestination = Route.OnBoardingScreen.route
    ) {
      composable(route = Route.OnBoardingScreen.route) {
        val viewModel: OnBoardingViewModel = hiltViewModel()
        OnBoardingScreen(
          event = viewModel::onEvent
        )
      }
    }

    navigation(
      route = Route.HomeScreenNavigation.route,
      startDestination = Route.OrderListScreen.route
    ) {
      composable(
        route = Route.OrderListScreen.route
      ) {
        NewOrderScreen()
      }
    }
  }
}

// (/app/onboarding)
// (/app/home)