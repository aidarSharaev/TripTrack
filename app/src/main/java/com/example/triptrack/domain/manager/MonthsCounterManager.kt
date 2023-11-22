package com.example.triptrack.domain.manager

import kotlinx.coroutines.flow.Flow

interface MonthsCounterManager {

    fun readCurrentMonth(): Flow<Int>
    suspend fun saveCurrentMonth(month: Int)
}
