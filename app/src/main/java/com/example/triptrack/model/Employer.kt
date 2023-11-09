package com.example.triptrack.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.triptrack.utils.Constants.EMPLOYER_TABLE

@Entity(tableName = EMPLOYER_TABLE)
data class Employer(
    @PrimaryKey(autoGenerate = false)
    val description: String,
    val orderCount: Int,
    val amountMoney: Int,
)
