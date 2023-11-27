package com.example.triptrack.presentation.navgraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.triptrack.presentation.order_screen.OrderScreen
import com.example.triptrack.presentation.order_screen.OrderViewModel
import com.example.triptrack.screen.home.HomeScreen
import com.example.triptrack.screen.home.HomeScreenViewModel
import com.example.triptrack.screen.new_order_screen.NewOrderScreen
import com.example.triptrack.screen.new_order_screen.NewOrderViewModel
import com.example.triptrack.screen.profile.ProfileScreen
import com.example.triptrack.screen.profile.ProfileViewModel
import com.example.triptrack.screen.profile.UserScreen
import com.example.triptrack.screen.profile.UserViewModel
import com.example.triptrack.screen.statistics.StatisticsScreen
import com.example.triptrack.screen.statistics.StatisticsViewModel

@Composable
fun HomeNavigator(
    startDestination: String,
) {
    val navItemList = remember {
        mutableStateListOf(
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
        mutableIntStateOf(0)
    }

    selectedItem = when (backStack?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.StatScreen.route -> 1
        Route.UserScreen.route -> 2
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
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(bottom = bottomPadding),
        ) {
            composable(route = Route.StatScreen.route) {
                val viewModel: StatisticsViewModel = hiltViewModel()
                StatisticsScreen(
                    navController = navController,
                    viewModel = viewModel,
                )
            }
            homeGraph(navController = navController)
            userGraph(navController = navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
fun NavGraphBuilder.userGraph(
    navController: NavController,
) {
    composable(route = Route.UserScreen.route) {
        val viewModel: UserViewModel = hiltViewModel()
        UserScreen(navController = navController, viewModel = viewModel)
    }
    composable(route = Route.ProfileScreen.route) {
        val viewModel: ProfileViewModel = hiltViewModel()
        ProfileScreen(viewModel = viewModel, navigateUp = { navController.navigateUp() })
    }
    composable(route = Route.OrderListScreen.route) {
        val viewModel: OrderViewModel = hiltViewModel()
        OrderScreen(viewModel = viewModel)
    }
    composable(route = Route.EmployerListScreen.route) {
        // val viewModel: ProfileViewModel = hiltViewModel()
        // ProfileScreen(navController = navController, viewModel = viewModel)
    }
}

fun NavGraphBuilder.homeGraph(
    navController: NavController,
) {
    composable(route = Route.HomeScreen.route) {
        val viewModel: HomeScreenViewModel = hiltViewModel()
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
        val viewModel: NewOrderViewModel = hiltViewModel()
        NewOrderScreen(
            viewModel = viewModel,
            navigateUp = { navController.navigateUp() },
        )
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
        }

        2 -> {
            navigateToTap(
                navController = navController,
                route = Route.UserScreen.route,
            )
        }

        else -> {
            navigateToTap(
                navController = navController,
                route = Route.HomeScreen.route,
            )
        }
    }
}
