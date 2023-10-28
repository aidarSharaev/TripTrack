package com.example.triptrack.presentation.order_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triptrack.domain.usecaces.local_data.LocalDataUseCases
import com.example.triptrack.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
  private val localDataUseCases: LocalDataUseCases
): ViewModel(){

  private val _state = mutableStateOf(OrderState())
  val state: State<OrderState> = _state

  init {
    viewModelScope.launch {
      repeat(40) { index->
        Log.d("aaa", index.toString())
        localDataUseCases.insertOrder(
          Order(
            id = index + 123,
            route = "Kazan",
            payment = true,
            tax = true,
            date = "23-10-${index}",
            income = index*5+index,
            profit = index*5+index
          )
        )
      }
    }
  }

  val orders = localDataUseCases.getOrder()

}