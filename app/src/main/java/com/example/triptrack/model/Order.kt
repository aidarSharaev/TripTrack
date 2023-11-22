package com.example.triptrack.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.triptrack.utils.Constants.ORDER_TABLE

@Entity(
    tableName = ORDER_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = Employer::class,
            childColumns = ["employerName"],
            parentColumns = ["description"],
        ),
    ],
)
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // айдишка
    val employerName: String, // заказчик
    val route: String, // маршрут
    val payment: Boolean, // оплата
    val tax: Boolean, // налог
    val date: String, // дата
    val income: Int, // доход
    val wastes: Int = 0, // расход
    val profit: Int, // маршрут
)
