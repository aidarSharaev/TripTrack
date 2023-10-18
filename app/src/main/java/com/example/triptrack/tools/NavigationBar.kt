package com.example.triptrack.tools

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.triptrack.navigation.bottomNavGraph
import com.example.triptrack.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun StartScreen() {
  val navController: NavHostController = rememberNavController()
//  Scaffold (
//    bottomBar = {bottomBar(navController = navController)},
//    topBar = {topBar()}
//  ) {
//    bottomNavGraph(navController = navController)
//  }
}


@Composable
fun bottomBar(navController: NavController) {
//  val screens = listOf(
//    Screen.MainScreen,
//    Screen.StatisticsScreen,
//    Screen.ProfileScreen
//  )
//  val navBackStackEntry by navController.currentBackStackEntryAsState()
//  Log.d("tag", navBackStackEntry.toString())
//  val currentDestination = navBackStackEntry?.destination
//  Log.d("tag", currentDestination.toString())
//
//  NavigationBar {
//    screens.forEach { screen ->
//      AddItem(
//        screen = screen,
//        currentDestination = currentDestination,
//        navController = navController)
//    }
//  }
}

@Composable
fun RowScope.AddItem(
  screen: Screen,
  currentDestination: NavDestination?,
  navController: NavController
) {
//  NavigationBarItem(
//    label = {
//      Text(text = screen.title)
//    },
//    icon = {
//      Icon(
//        imageVector = screen.icon,
//        contentDescription = "Navigation Icon"
//      )
//    },
//    selected = currentDestination?.hierarchy?.any{
//      it.route == screen.route
//    } == true,
//    onClick = {
//      navController.navigate(screen.route) {
//        popUpTo(navController.graph.findStartDestination().id)
//        launchSingleTop = true
//      }
//    }
//  )
}