package com.example.triptrack.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.triptrack.data.local.dao.EmployerDao
import com.example.triptrack.data.local.dao.OrderDao
import com.example.triptrack.domain.remote.LocalDataRepository
import com.example.triptrack.model.Employer
import com.example.triptrack.model.Order
import kotlinx.coroutines.flow.Flow

class LocalDataRepositoryImpl(
  private val orderDao: OrderDao,
  private val employerDao: EmployerDao
) : LocalDataRepository {

  override fun getOrder(): Flow<PagingData<Order>> {
    return Pager(
      config = PagingConfig(pageSize = 6),
      pagingSourceFactory = {
        orderDao.loadAllOrdersPaged()
      }
    ).flow
  }

  override fun getEmployer(): Flow<PagingData<Employer>> {
    return Pager(
      config = PagingConfig(pageSize = 5),
      pagingSourceFactory = {
        employerDao.loadAllEmployersPaged()
      }
    ).flow
  }

}





