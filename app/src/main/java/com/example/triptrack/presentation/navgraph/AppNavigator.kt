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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigator(

) {

  val navItemList = remember {
    mutableStateListOf<NavItemList>(
      NavItemList(Icons.Default.Home, "Главная"),
      NavItemList(Icons.Default.Info, "Подробности"),
      NavItemList(Icons.Default.AccountCircle, "Профиль")
    )
  }

  val navController = rememberNavController()
  val backStack = navController.currentBackStackEntryAsState().value

  var selectedItem by rememberSaveable {
    mutableStateOf(0)
  }

  selectedItem = when(backStack?.destination?.route) {
    Route.HomeScreenNavigation.route -> 0
    Route.StatScreenNavigation.route -> 1
    Route.ProfileScreenNavigation.route -> 2
    else -> 0
  }

  Scaffold(
    bottomBar = {
      BottomBar(navItemList = navItemList, selectedItem = selectedItem, onItemClick = {})
    }

    ) {
    Column(modifier = Modifier.padding(it)) {

    }
  }

}