package com.example.triptrack.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triptrack.domain.usecaces.local_data.order.OrderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val orderUseCases: OrderUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        downloadFacts() // TODO("facts")
    }

    // TODO("fun facts")
    private fun downloadFacts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(facts = orderUseCases.orderCount())
        }
    }
}
