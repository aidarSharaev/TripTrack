package com.example.triptrack.presentation.navgraph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.triptrack.presentation.order_screen.NewOrderScreen
import com.example.triptrack.screen.home.HomeScreen
import com.example.triptrack.screen.home.HomeScreenViewModel

@Composable
fun HomeNavigator(
    startDestination: String,
) {
    val navItemList = remember {
        mutableStateListOf<NavItemList>(
            NavItemList(Icons.Default.Home, "Главная"),
            NavItemList(Icons.Default.Info, "Подробности"),
            NavItemList(Icons.Default.AccountCircle, "Профиль"),
        )
    }

    val navController = rememberNavController()
    val backStack = navController.currentBackStackEntryAsState().value

    val bottomNavVisible = remember {
        mutableStateOf(true)
    }
    bottomNavVisible.value = navController.currentDestination?.route != Route.NewOrderScreen.route

    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when (backStack?.destination?.route) {
        Route.HomeScreenNavigation.route -> 0
        Route.StatScreenNavigation.route -> 1
        Route.ProfileScreenNavigation.route -> 2
        else -> 0
    }

    Scaffold(
        bottomBar = {
            if (bottomNavVisible.value) {
                BottomBar(
                    navItemList = navItemList,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        navigateChoice(index = index, navController = navController)
                    },
                )
            }
        },

    ) {
        Column(modifier = Modifier.padding(it)) {
        }
        NavHost(
            navController = navController,
            startDestination = startDestination,
        ) {
            navigation(
                route = Route.HomeScreenNavigation.route,
                startDestination = Route.HomeScreen.route,
            ) {
                composable(route = Route.HomeScreen.route) {
                    val viewModel = hiltViewModel<HomeScreenViewModel>()
                    val state = viewModel.state
                    HomeScreen(
                        state = state,
                        navController = navController,
                        onClick = {
                            navController.navigate(route = Route.NewOrderScreen.route)
                        },
                    )
                }
                composable(route = Route.NewOrderScreen.route) {
                    NewOrderScreen()
                }
            }
            navigation(
                route = Route.StatScreenNavigation.route,
                startDestination = Route.StatScreen.route,
            ) {
                composable(route = Route.StatScreen.route) {
                    StatNavigator()
                }
            }
            navigation(
                route = Route.ProfileScreenNavigation.route,
                startDestination = Route.StatScreen.route,
            ) {
                composable(route = Route.ProfileScreen.route) {
                    ProfileNavigator()
                }
            }
        }
    }
}

fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route = route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

fun navigateChoice(index: Int, navController: NavController) {
    when (index) {
        0 -> {
            navigateToTap(
                navController = navController,
                route = Route.HomeScreen.route,
            )
        }

        1 -> {
            navigateToTap(
                navController = navController,
                route = Route.StatScreen.route,
            )
            // new graph?
        }

        2 -> {
            navigateToTap(
                navController = navController,
                route = Route.HomeScreen.route,
            )
            // new graph?
        }

        else -> {
            navigateToTap(
                navController = navController,
                route = Route.HomeScreen.route,
            )
        }
    }
}
