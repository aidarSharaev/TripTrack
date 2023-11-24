package com.example.triptrack.screen.statistics

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import com.example.triptrack.domain.usecaces.local_data.order.OrderUseCases
import com.example.triptrack.utils.DATE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val orderUseCases: OrderUseCases,
) : ViewModel() {

    private val _uiState = MutableStateFlow(StatisticsState())
    var uiState: StateFlow<StatisticsState> = _uiState.asStateFlow()

    private var _tempMonth = ""
    private var _tempYear = ""

    private val arrayList: MutableList<Point> = mutableStateListOf(
        Point(0f, 0f),
        Point(0f, 0f),
        Point(0f, 0f),
        Point(0f, 0f),
        Point(0f, 0f),
    )

    var maxValue = -1
    var minValue = Int.MAX_VALUE
    private val currentDate = DATE.currentDate.split("/")

    init {
        _uiState.value.month = if (currentDate[1].length == 1) "0$currentDate[1]" else currentDate[1]
        _uiState.value.year = currentDate[2]
        _tempMonth = _uiState.value.month
        _tempYear = _uiState.value.year
        determinePreviousMonth()
        getOrderMonth()
        addLabelForAxis()
    }

    /* сбор всех поездок к определенному месяцу
    за 5ый месяц - 7
    за 4ый месяц - 7+10
    за 3ый месяц - 7+10+23
    за 20й месяц - 7+10+23+12
    к этому месяцу - 7+10+23+12+43

    общее число заказов
    * */
    private fun getOrderMonth() {
        orderUseCases.getOrderCount()
            .onEach { count ->
                _uiState.update { newState ->
                    newState.copy(totalOrderCount = count)
                }
                addLabelForAxis()
            }.launchIn(viewModelScope)
        orderUseCases.getCountOfOrdersMonth(pair = _uiState.value.monthYear[4])
            .onEach { count ->
                _uiState.update { newState ->
                    newState.copy(orders5MonthsAgo = count)
                }
                addLabelForAxis()
            }.launchIn(viewModelScope)
        orderUseCases.getCountOfOrdersMonth(pair = _uiState.value.monthYear[3])
            .onEach { count ->

                _uiState.update { newState ->
                    newState.copy(orders4MonthsAgo = count)
                }
                addLabelForAxis()
            }.launchIn(viewModelScope)
        orderUseCases.getCountOfOrdersMonth(pair = _uiState.value.monthYear[2])
            .onEach { count ->

                _uiState.update { newState ->
                    newState.copy(orders3MonthsAgo = count)
                }
                addLabelForAxis()
            }.launchIn(viewModelScope)
        orderUseCases.getCountOfOrdersMonth(pair = _uiState.value.monthYear[1])
            .onEach { count ->
                _uiState.update { newState ->
                    newState.copy(ordersToLastMonth = count)
                }
                addLabelForAxis()
            }.launchIn(viewModelScope)
        orderUseCases.getCountOfOrdersMonth(pair = _uiState.value.monthYear[0])
            .onEach { count ->

                _uiState.update { newState ->
                    newState.copy(ordersByThisMonth = count)
                }
                addLabelForAxis()
            }.launchIn(viewModelScope)
    }

    fun put() {
        Log.d("AAAAr", _uiState.value.ordersByThisMonth.toString())
        Log.d("AAAAr", _uiState.value.ordersToLastMonth.toString())
        Log.d("AAAAr", _uiState.value.orders3MonthsAgo.toString())
        Log.d("AAAAr", _uiState.value.orders4MonthsAgo.toString())
        Log.d("AAAAr", _uiState.value.orders5MonthsAgo.toString())
        Log.d("AAAAr", _uiState.value.totalOrderCount.toString())
        Log.d("AAAAr", "")
        addLabelForAxis()
    }

    private fun determinePreviousMonth() {
        val list = mutableListOf<CustomPair>()
        repeat(5) {
            list.add(CustomPair(month = _tempMonth, year = _tempYear))

            if (_tempMonth == "01") {
                _tempMonth = "12"
                _tempYear = ((_tempYear.toInt()) - 1).toString()
            } else {
                _tempMonth = (_tempMonth.toInt() - 1).toString()
                if (_tempMonth.length == 1) _tempMonth = "0$_tempMonth"
            }
        }
        _uiState.update {
            it.copy(
                monthYear = list,
            )
        }
        list.forEach {
            Log.d("AAAAr", "${it.month} ${it.year}")
        }
    }

    private fun addLabelForAxis() {
        val intList = listOf(
            uiState.value.totalOrderCount,
            uiState.value.ordersByThisMonth,
            uiState.value.ordersToLastMonth,
            uiState.value.orders3MonthsAgo,
            uiState.value.orders4MonthsAgo,
            uiState.value.orders5MonthsAgo,
        )

        _uiState.update {
            it.copy(
                listOrders = (intList.slice(1..intList.size - 1).sorted().toSet()),
            )
        }

        var temp = 0

        repeat(5) { index ->
            arrayList[index] = Point(
                (4 - index).toFloat(),
                (intList.first() - temp).toFloat(),
            )
            if (index != 4) {
                temp += intList[index + 1]
            }
        }

        maxValue = intList.first()
        minValue = intList.first() - temp

        _uiState.update {
            it.copy(
                totalCount = arrayList.reversed().toList(),
            )
        }

        repeat(5) { index ->
            arrayList[index] = Point(
                uiState.value.monthYear[4 - index].month.toFloat(),
                intList[5 - index].toFloat(),
            )
        }

        arrayList.forEach {
            Log.d("AAAA", "${it.x} ${it.y}")
        }

        _uiState.update { newState ->
            newState.copy(
                monthlyCount = arrayList.toList(),
            )
        }
    }
}
