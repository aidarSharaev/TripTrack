package com.example.triptrack.screen.new_order_screen

import com.example.triptrack.utils.DATE

data class NewOrderState(
    var employerName: String = "",
    var tax: Boolean = false,
    var payment: Boolean = false,
    var employerList: List<String> = emptyList(),
    var total: Int = 0,
    var wastes: Int = 0,
    var income: Int = 0,
    var date: String = DATE.currentDate
)
