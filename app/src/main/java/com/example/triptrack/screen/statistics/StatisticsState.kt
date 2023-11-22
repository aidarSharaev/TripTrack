package com.example.triptrack.screen.statistics

import co.yml.charts.common.model.Point

data class CustomPair(
    val month: String,
    val year: String,
    var ordersCount: Int = 0,
)

data class StatisticsState(
    // val monthlyOrders: MutableList<MonthlyOrders> = mutableListOf(),
    // val ordersPerMonth: MutableList<OrdersPerMonth> = mutableListOf(),
    val pointsDataPerMonth: MutableList<Point> = mutableListOf(),
    val pointsDataMonthly: MutableList<Point> = mutableListOf(),
    val monthlyOrdersLabel: MutableList<Float> = mutableListOf(),
    val ordersPerMonthLabel: MutableList<String> = mutableListOf(),
    var month: String = "",
    var year: String = "",
    val month_year: List<CustomPair> = emptyList(),
    var ordersByThisMonth: Int = 0,
    var ordersToLastMonth: Int = 0,
    var orders3MonthsAgo: Int = 0,
    var orders4MonthsAgo: Int = 0,
    var orders5MonthsAgo: Int = 0,
    var totalOrderCount: Int = 0,
)
