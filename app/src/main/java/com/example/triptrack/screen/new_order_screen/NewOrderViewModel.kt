package com.example.triptrack.screen.new_order_screen

import android.annotation.SuppressLint
import androidx.compose.material3.TooltipState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.ViewModel
import co.yml.charts.common.extensions.isNotNull
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

object DATE {
    @SuppressLint("SimpleDateFormat")
    val simpleDataFormat = SimpleDateFormat("dd/M/yyyy")
    val currentDate: String by mutableStateOf(simpleDataFormat.format(Date()))
}

class NewOrderViewModel
@Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(NewOrderState())
    val uiState: StateFlow<NewOrderState> = _uiState.asStateFlow()

    var profitValue by mutableStateOf(value = "")
        private set
    var costValue by mutableStateOf(value = "")
        private set
    var expanded by mutableStateOf(value = false)
        private set
    var textFieldSize by mutableStateOf(value = Size.Zero)
        private set
    val taxTooltipState by mutableStateOf(TooltipState(isPersistent = true))
    val paymentTooltipState by mutableStateOf(TooltipState(isPersistent = true))

    fun costUpdate(costValue: String) {
        val cost = costValue.toInt()
        this.costValue = costValue
        _uiState.update { currentState ->
            currentState.copy(
                cost = cost,
                total = (_uiState.value.profit - cost),
            )
        }
    }

    fun profitUpdate(profitValue: String) {
        val profit = profitValue.toInt()
        this.profitValue = profitValue
        _uiState.update { currentState ->
            currentState.copy(
                profit = profit,
                total = (profit - _uiState.value.cost),
            )
        }
    }

    fun selectedEmployerUpdate(employer: String) {
        _uiState.update { newOrderState ->
            newOrderState.copy(
                selectedEmployer = employer,
            )
        }
    }

    fun expandedUpdate(expanded: Boolean) {
        this.expanded = expanded
    }

    fun textFieldSizeUpdate(coordinates: LayoutCoordinates) {
        textFieldSize = coordinates.size.toSize()
    }

    fun taxCheckedUpdate(checked: Boolean) {
        _uiState.update { newOrderState ->
            newOrderState.copy(tax = checked)
        }
    }

    fun paymentCheckedUpdate(checked: Boolean) {
        _uiState.update { newOrderState ->
            newOrderState.copy(pay = checked)
        }
    }

    fun orderDateChange(date: String) {
        _uiState.update { newState ->
            if (checkDateValid(date)) {
                newState.copy(
                    date = date,
                )
            } else {
                newState.copy(
                    date = DATE.currentDate,
                )
            }
        }
    }

    private fun checkDateValid(date: String): Boolean {
        val currDate: Date? = DATE.simpleDataFormat.parse(DATE.currentDate)
        val orderDate: Date? = DATE.simpleDataFormat.parse(date)
        if (currDate.isNotNull() && orderDate.isNotNull()) {
            val cmp = currDate?.compareTo(orderDate)
            cmp.let {
                return when {
                    cmp!! >= 0 -> {
                        true
                    }

                    else -> {
                        false
                    }
                }
            }
        }
        return false
    }

    fun changingProfitCostWithButton(text: String) {
        val value = text.substring(1).toIntOrNull()
        val field: String = if (text[0] == '+') {
            profitValue
        } else {
            costValue
        }
        val fieldNotEmpty = field.toIntOrNull()
        fieldNotEmpty?.let {
            if (text[0] == '+') {
                profitUpdate((fieldNotEmpty + value!!).toString())
            } else {
                costUpdate((fieldNotEmpty + value!!).toString())
            }
        } ?: run {
            if (text[0] == '+') {
                profitUpdate((value!!).toString())
            } else {
                costUpdate((value!!).toString())
            }
        }
    }
}
