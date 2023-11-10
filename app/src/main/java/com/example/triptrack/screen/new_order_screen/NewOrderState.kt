package com.example.triptrack.screen.new_order_screen

data class NewOrderState(
    var selectedEmployer: String = "",
    var tax: Boolean = false,
    var pay: Boolean = false,
    var employerList: MutableSet<String> = mutableSetOf("булгарпиво", "степан", "ермак"),
    var total: Int = 0,
    var cost: Int = 0,
    var profit: Int = 0,
    var date: String = DATE.currentDate
)
