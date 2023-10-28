package com.example.triptrack.domain.usecaces.local_data.order

import androidx.paging.PagingSource
import com.example.triptrack.data.local.dao.OrderDao
import com.example.triptrack.model.Order

class SelectOrder(
  private val orderDao: OrderDao
) {

  operator fun invoke() : PagingSource<Int, Order> {
    return orderDao.loadAllOrdersPaged()
  }

}

//todo rename
