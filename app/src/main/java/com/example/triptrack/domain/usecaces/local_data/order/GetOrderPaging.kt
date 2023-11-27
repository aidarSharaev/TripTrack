package com.example.triptrack.domain.usecaces.local_data.order

import androidx.paging.PagingData
import com.example.triptrack.domain.repository.OrderRepository
import com.example.triptrack.model.Order
import kotlinx.coroutines.flow.Flow

class GetOrderPaging(
    private val orderRepository: OrderRepository,
) {

    operator fun invoke(): Flow<PagingData<Order>> {
        return orderRepository.loadAllOrdersPaged()
    }
}
