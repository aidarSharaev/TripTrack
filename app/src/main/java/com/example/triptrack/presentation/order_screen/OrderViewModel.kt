package com.example.triptrack.presentation.order_screen // ktlint-disable package-name

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.triptrack.domain.usecaces.local_data.order.OrderUseCases
import com.example.triptrack.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderUseCases: OrderUseCases,
) : ViewModel() {

    lateinit var orders: Flow<PagingData<Order>>

    init {
        paging()
    }

    private fun paging() {
        orders = orderUseCases.getOrderPaging()
    }
}
