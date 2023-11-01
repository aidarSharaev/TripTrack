package com.example.triptrack.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.triptrack.data.local.dao.OrderDao
import com.example.triptrack.domain.repository.OrderRepository
import com.example.triptrack.model.Order
import com.example.triptrack.remote.dto.OrdersPagingSource
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(
  private val orderDao: OrderDao
): OrderRepository {
  override fun loadAllOrdersPaged(): Flow<PagingData<Order>> {
    return Pager(
      config = PagingConfig(pageSize = 20),
      pagingSourceFactory = {
        OrdersPagingSource(
          orderDao = orderDao
        )
      }
    ).flow
  }
}