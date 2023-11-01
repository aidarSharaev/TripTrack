package com.example.triptrack.domain.usecaces.local_data.order

import com.example.triptrack.data.local.dao.OrderDao

class OrderCount(
  private val orderDao: OrderDao
) {

  suspend operator fun invoke() : Int{
    return orderDao.getOrderCount()
  }

}