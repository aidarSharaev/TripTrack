package com.example.triptrack.domain.usecaces.local_data.order

import com.example.triptrack.data.local.dao.OrderDao
import com.example.triptrack.model.Order

class GetOrderById(
  private val orderDao: OrderDao
) {

  suspend operator fun invoke(id: Int) : Order {
    return orderDao.getOrderById(id = id)
  }

}

//todo rename
