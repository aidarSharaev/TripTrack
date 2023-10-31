package com.example.triptrack.presentation.navgraph

sealed class Route(
  val route: String
) {

  object AppStartNavigation : Route(route = "appStartNavigation")
  object OnBoardingScreen : Route(route = "onBoardingScreen")
  object HomeScreenNavigation : Route(route = "homeScreenNavigation")
  object HomeScreen : Route(route = "homeScreen")
  object StatScreenNavigation : Route(route = "statScreenNavigation")
  object StatScreen : Route(route = "statScreen")
  object ProfileScreenNavigation : Route(route = "profileScreenNavigation")
  object ProfileScreen : Route(route = "profileScreen")
  object NewOrderScreen : Route(route = "newOrderScreen")
  object OrderListScreen : Route(route = "onBoardingScreen")
  object EmployerListScreen : Route(route = "employerListScreen")
  object NewEmployerScreen : Route(route = "newEmployerScreen")
  object FirstEntryScreen : Route(route = "firstEntryScreen")

}

