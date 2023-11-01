package com.example.triptrack.data.repository

import androidx.paging.PagingSource
import com.example.triptrack.data.local.dao.OrderDao
import com.example.triptrack.domain.repository.OrderRepository
import com.example.triptrack.model.Order

class OrderRepositoryImpl(
  private val orderDao: OrderDao
): OrderRepository {

  override fun loadAllOrdersPaged(): PagingSource<Int, Order> {
    return orderDao.loadAllOrdersPaged()
  }

  override suspend fun getOrderById(id: Int): Order {
    return orderDao.getOrderById(id = id)
  }

  override suspend fun insertOrder(order: Order) {
    orderDao.insertOrder(order = order)
  }

  override suspend fun deleteOrderById(order: Order) {
    orderDao.deleteOrderById(order = order)
  }

}