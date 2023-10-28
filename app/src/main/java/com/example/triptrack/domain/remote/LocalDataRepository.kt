package com.example.triptrack.domain.remote

import androidx.paging.PagingData
import com.example.triptrack.model.Employer
import com.example.triptrack.model.Order
import kotlinx.coroutines.flow.Flow

interface LocalDataRepository{

  fun getOrder() : Flow<PagingData<Order>>

  fun getEmployer(): Flow<PagingData<Employer>>

}