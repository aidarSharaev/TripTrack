package com.example.triptrack.presentation.navgraph

sealed class Route(
    val route: String,
) {

    object AppStartNavigation : Route(route = "appStartNavigation")
    object ProfileScreenNavigation : Route(route = "profileScreenNavigation")
    object HomeScreenNavigation : Route(route = "homeScreenNavigation")
    object StatScreenNavigation : Route(route = "statScreenNavigation")

    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object HomeScreen : Route(route = "homeScreen")

    object StatScreen : Route(route = "statScreen")

    object ProfileScreen : Route(route = "profileScreen")
    object NewOrderScreen : Route(route = "newOrderScreen")
    object OrderListScreen : Route(route = "onBoardingScreen")
    object EmployerListScreen : Route(route = "employerListScreen")
    object AccountScreen : Route(route = "accountScreen")

    object FirstEntryScreen : Route(route = "firstEntryScreen")
}
