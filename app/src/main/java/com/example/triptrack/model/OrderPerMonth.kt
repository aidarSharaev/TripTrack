package com.example.triptrack.model

abstract class OrderForPlot(
    open val id: Int,
)

data class OrderPerMonth(
    override val id: Int = 0,
    val month: String,
    val count: Int = 0,
) : OrderForPlot(id = id)

data class OrderByMonth(
    override val id: Int = 0,
    val month: Int = 0,
    val count: Int = 0,
) : OrderForPlot(id = id)


