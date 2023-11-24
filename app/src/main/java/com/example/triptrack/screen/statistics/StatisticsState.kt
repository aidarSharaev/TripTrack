package com.example.triptrack.screen.statistics

import co.yml.charts.common.model.Point

data class CustomPair(
    val month: String,
    val year: String,
)

data class StatisticsState(
    val totalCount: List<Point> = emptyList(),
    val monthlyCount: List<Point> = emptyList(),
    var month: String = "",
    var year: String = "",
    val monthYear: List<CustomPair> = emptyList(),
    var ordersByThisMonth: Int = 0,
    var ordersToLastMonth: Int = 0,
    var orders3MonthsAgo: Int = 0,
    var orders4MonthsAgo: Int = 0,
    var orders5MonthsAgo: Int = 0,
    var totalOrderCount: Int = 0,
    var listOrders: Set<Int> = emptySet(),
)
