package com.example.triptrack.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.triptrack.utils.Constants.EMPLOYER_TABLE

@Entity(tableName = EMPLOYER_TABLE)
data class Employer(
    @PrimaryKey
    val description: String, // имя заказчика
    val orderCount: Int = 0, // количество заказов
    val amountMoney: Int = 0, // количество денег
)

