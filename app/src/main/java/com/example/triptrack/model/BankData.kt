package com.example.triptrack.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.triptrack.utils.Constants.BANK_DATA_TABLE

@Entity(tableName = BANK_DATA_TABLE)
data class BankData(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val requisites: String,
    val snils: String,
    val inn: String,
    val passport: String,
)
