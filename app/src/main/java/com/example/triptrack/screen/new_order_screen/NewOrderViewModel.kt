package com.example.triptrack.screen.new_order_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class NewOrderViewModel
@Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(NewOrderState())
    val uiState: StateFlow<NewOrderState> = _uiState.asStateFlow()

    var profitValue by mutableStateOf("0")

    var costValue by mutableStateOf("0")

    fun costUpdate(costValue: String) {
        try {
            val cost = costValue.toInt()
            this.costValue = costValue
            _uiState.update { currentState ->
                currentState.copy(
                    cost = cost,
                    total = (_uiState.value.profit - cost),
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("TAG", "EXCEPTION")
        }
    }

    fun profitUpdate(profitValue: String) {
        Log.d("TAG", "profit")
        try {
            val profit = profitValue.toInt()
            this.profitValue = profitValue
            _uiState.update { currentState ->
                currentState.copy(
                    profit = profit,
                    total = (profit - _uiState.value.cost),
                )
            }
        } catch (e: Exception) {
            // e.printStackTrace()
            Log.d("TAG", "EXCEPTION")
        }
    }
}
