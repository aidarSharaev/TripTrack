package com.example.triptrack.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import java.text.SimpleDateFormat
import java.util.Date

object DATE {
    @SuppressLint("SimpleDateFormat")
    val simpleDataFormat = SimpleDateFormat("dd/M/yyyy")
    val currentDate: String by mutableStateOf(simpleDataFormat.format(Date()))
}

object Constants {

    const val PROFILE_INFORMATION_TABLE = "profile_information_table"
    const val BANK_DATA_TABLE = "bank_data_table"
    const val EMPLOYER_TABLE = "employer_table"
    const val ORDER_TABLE = "order_table"
    const val TRIP_TRACK_DATABASE = "trip_track_database"
    const val MONTHLY_ORDERS = "monthly_orders"

    const val DATA_STORE = "data_store"
    const val APP_ENTRY = "app_entry"
    const val CURRENT_MONTH = "current_month"

    val MONTHS = mapOf(
        "1" to "Январь",
        "2" to "Февраль",
        "3" to "Март",
        "4" to "Апрель",
        "5" to "Май",
        "6" to "Июнь",
        "7" to "Июль",
        "8" to "Август",
        "9" to "Сентябрь",
        "10" to "Октябрь",
        "11" to "Ноябрь",
        "12" to "Декабрь",
    )

    val MONEY_VALUES_UP = listOf("+3000", "+1000", "+500")
    val MONEY_VALUES_DOWN = listOf("-2000", "-1000", "-500")
}
