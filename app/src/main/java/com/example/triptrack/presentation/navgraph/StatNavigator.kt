package com.example.triptrack.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.triptrack.screen.statistics.StatisticsScreen

@Composable
fun StatNavigator() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.StatScreen.route,
    ) {
        composable(route = Route.StatScreen.route) {
            StatisticsScreen(
                navController = navController,
            )
        }
    }
}
