package com.example.triptrack.presentation.order_screen // ktlint-disable package-name

import androidx.paging.PagingData
import com.example.triptrack.model.Order
import kotlinx.coroutines.flow.Flow

data class OrderState(
    var orders: Flow<PagingData<Order>>? = null,
)
