package com.example.triptrack.domain.usecaces.local_data

import com.example.triptrack.domain.usecaces.local_data.employer.GetEmployerPaging
import com.example.triptrack.domain.usecaces.local_data.order.GetOrderPaging
import com.example.triptrack.domain.usecaces.local_data.order.InsertOrder

data class LocalDataUseCases(
  val getOrder: GetOrderPaging,
  val getEmployer: GetEmployerPaging,
  val insertOrder: InsertOrder,
  //todo delete selectId
)