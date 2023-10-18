package com.example.triptrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.triptrack.screen.home.MainScreen
import com.example.triptrack.screen.profile.ProfileScreen
import com.example.triptrack.screen.statistics.StatisticsScreen

@Composable
fun bottomNavGraph(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = Screen.MainScreen.route)
  {
    composable(route = Screen.MainScreen.route) {
      MainScreen(navController)
    }
    composable(route = Screen.StatisticsScreen.route) {
      StatisticsScreen(navController)
    }
    composable(route = Screen.ProfileScreen.route) {
      ProfileScreen(navController)
    }
  }
}
