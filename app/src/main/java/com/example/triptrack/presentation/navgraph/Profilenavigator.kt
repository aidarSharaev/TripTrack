package com.example.triptrack.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.ProfileScreen.route
    ) {
        composable(route = Route.ProfileScreen.route) {

        }
        composable(route = Route.OrderListScreen.route) {

        }
        composable(route = Route.EmployerListScreen.route) {

        }
        composable( route = Route.AccountScreen.route) {

        }

    }
}
