package com.example.triptrack.domain.usecaces.local_data.order

import com.example.triptrack.data.local.dao.OrderDao
import com.example.triptrack.model.Order

class DeleteOrder(
    private val orderDao: OrderDao,
) {

    suspend operator fun invoke(order: Order) {
        orderDao.deleteOrderById(order = order)
    }
}
