package com.example.triptrack.domain.repository

import androidx.paging.PagingData
import com.example.triptrack.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun loadAllOrdersPaged(): Flow<PagingData<Order>>
}
