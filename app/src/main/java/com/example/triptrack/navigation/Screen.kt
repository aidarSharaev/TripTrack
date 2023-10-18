// можно создать ресурс файлы

package com.example.triptrack.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
  val route: String,
  val title: String,
  val icon: ImageVector
) {
  object MainScreen: Screen(
    route = "main",
    title = "Main",
    icon = Icons.Default.Home
  )
  object StatisticsScreen: Screen(
    route = "statistics",
    title = "Statistics",
    icon = Icons.Default.Star
  )
  object ProfileScreen: Screen(
    route = "profile",
    title = "Profile",
    icon = Icons.Default.Person
  )
}
