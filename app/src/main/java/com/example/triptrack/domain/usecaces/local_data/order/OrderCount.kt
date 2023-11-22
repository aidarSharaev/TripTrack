package com.example.triptrack.domain.usecaces.local_data.order

import com.example.triptrack.data.local.dao.OrderDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OrderCount(
    private val orderDao: OrderDao,
) {

    operator fun invoke(): Flow<Int>  {
        return orderDao.getOrderCount()
    }
}
