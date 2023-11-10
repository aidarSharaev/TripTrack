package com.example.triptrack.screen.new_order_screen

data class NewOrderState(
    val selectedEmployer: String = "",
    val tax: Boolean = false,
    val pay: Boolean = false,
    val employerList: MutableSet<String> = mutableSetOf("булгарпиво", "степан", "ермак"),
    val total: Int = 0,
    val cost: Int = 0,
    val profit: Int = 0,
)
