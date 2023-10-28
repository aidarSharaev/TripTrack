package com.example.triptrack.domain.usecaces.local_data.order

import android.util.Log
import androidx.paging.PagingData
import com.example.triptrack.domain.remote.LocalDataRepository
import com.example.triptrack.model.Order
import kotlinx.coroutines.flow.Flow

class GetOrderPaging(
  private val localDataRepository: LocalDataRepository
) {

  operator fun invoke () : Flow<PagingData<Order>> {

    return localDataRepository.getOrder()
  }

}