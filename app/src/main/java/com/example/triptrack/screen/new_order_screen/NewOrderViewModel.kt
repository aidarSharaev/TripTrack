package com.example.triptrack.screen.new_order_screen

import androidx.compose.material3.TooltipState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.extensions.isNotNull
import com.example.triptrack.domain.usecaces.local_data.employer.EmployerUseCases
import com.example.triptrack.domain.usecaces.local_data.order.OrderUseCases
import com.example.triptrack.model.Employer
import com.example.triptrack.model.Order
import com.example.triptrack.utils.DATE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject



@HiltViewModel
class NewOrderViewModel
@Inject constructor(
    private val orderUseCases: OrderUseCases,
    private val employerUseCases: EmployerUseCases,
) : ViewModel() {

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

    fun costUpdateNotState(costString: String) {
        val cost: Int? = costString.toIntOrNull()
        if (cost != null) {
            updateCostState(
                costString = costString,
                cost = -cost,
                total = (-cost + _uiState.value.income),
            )
        } else {
            updateCostState(
                total = (_uiState.value.income),
            )
        }
    }

    fun profitUpdateNotState(profitString: String) {
        val profit: Int? = profitString.toIntOrNull()
        if (profit != null) {
            updateProfitState(
                profitString = profitString,
                profit = profit,
                total = (profit + _uiState.value.wastes),
            )
        } else {
            updateProfitState(
                total = (_uiState.value.wastes),
            )
        }
    }

    private fun updateProfitState(
        profitString: String = "",
        profit: Int = 0,
        total: Int,
    ) {
        profitValue = profitString
        _uiState.update { currentState ->
            currentState.copy(
                income = profit,
                total = total,
            )
        }
    }

    private fun updateCostState(
        costString: String = "",
        cost: Int = 0,
        total: Int,
    ) {
        costValue = costString
        _uiState.update { currentState ->
            currentState.copy(
                wastes = cost,
                total = total,
            )
        }
    }

    init {
        collectEmployers()
    }

    private fun collectEmployers() {
        viewModelScope.launch {
            employerUseCases.getEmployers()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    println(e.stackTrace)
                }
                .collect { employerList ->
                    _uiState.update { newState ->
                        newState.copy(
                            employerList = employerList,
                        )
                    }
                }
        }
    }

    fun selectedEmployerUpdate(employer: String) {
        _uiState.update { newOrderState ->
            newOrderState.copy(employerName = employer)
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
            newOrderState.copy(payment = checked)
        }
    }

    fun orderDateChange(date: String) {
        _uiState.update { newState ->
            if (checkDateValid(date)) {
                newState.copy(date = date)
            } else {
                newState.copy(date = DATE.currentDate)
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
        val field = if (text[0] == '+') {
            profitValue
        } else {
            costValue
        }
        val fieldNotEmpty = field.toIntOrNull()
        fieldNotEmpty?.let {
            if (text[0] == '+') {
                profitUpdateNotState((fieldNotEmpty + value!!).toString())
            } else {
                costUpdateNotState((fieldNotEmpty + value!!).toString())
            }
        } ?: run {
            if (text[0] == '+') {
                profitUpdateNotState((value!!).toString())
            } else {
                costUpdateNotState((value!!).toString())
            }
        }
    }

    fun saveNewOrder(): Boolean {
        if (!checkingCorrectness()) return false
        viewModelScope.launch {
            val description = _uiState.value.employerName
            checkingExistenceEmployer(description = description)
            delay(300)
            insertOrder(description = description)
        }
        return true
    }

    private fun checkingExistenceEmployer(description: String) {
        viewModelScope.launch {
            val employer: Employer? =
                employerUseCases.getEmployerByName(description = description)
            employer?.let {
                insertEmployer(
                    Employer(
                        description = description,
                        orderCount = employer.orderCount + 1,
                        amountMoney = employer.amountMoney + _uiState.value.total,
                    ),
                )
            } ?: run {
                insertEmployer(
                    Employer(
                        description = description,
                        orderCount = 1,
                        amountMoney = _uiState.value.total,
                    ),
                )
            }
        }
    }

    private fun checkingCorrectness(): Boolean {
        return !(_uiState.value.employerName == "" || _uiState.value.income == 0)
    }

    private fun insertEmployer(employer: Employer) {
        viewModelScope.launch {
            employerUseCases.insertEmployer(
                employer = employer,
            )
        }
    }

    private fun insertOrder(description: String) {
        viewModelScope.launch {
            orderUseCases.insertOrder(
                Order(
                    employerName = description,
                    route = "Челны",
                    payment = _uiState.value.payment,
                    tax = _uiState.value.tax,
                    date = _uiState.value.date,
                    income = _uiState.value.income,
                    wastes = _uiState.value.wastes,
                    profit = (_uiState.value.income + _uiState.value.wastes),
                ),
            )
        }
    }
}
