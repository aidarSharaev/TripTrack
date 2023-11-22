package com.example.triptrack.screen.statistics

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val currentDate = DATE.currentDate.split("/")

    var maxBy by mutableFloatStateOf(0f)
        private set
    var minBy by mutableFloatStateOf(Float.MAX_VALUE)
        private set

    init {
        _uiState.value.month = if (currentDate[1].length == 1) "0$currentDate[1]" else currentDate[1]
        _uiState.value.year = currentDate[2]
        _tempMonth = _uiState.value.month
        _tempYear = _uiState.value.year
        determinePreviousMonth()
        getOrderMonth()
    }

    /* сбор всех поездок к определенному месяцу
    за 5ый месяц - 7
    за 4ый месяц - 7+10
    за 3ый месяц - 7+10+23
    за 20й месяц - 7+10+23+12
    к этому месяцу - 7+10+23+12+43

    заказы 5 месяцев назад
    * */
    private fun getOrderMonth() {
        orderUseCases.getOrderCount()
            .onEach { count ->
                _uiState.update { newState ->
                    newState.copy(totalOrderCount = count)
                }
            }.launchIn(viewModelScope)
        orderUseCases.getCountOfOrdersMonth(pair = _uiState.value.month_year[4]).onEach { count ->
            // month_year[4].ordersCount = count
            _uiState.update { newState ->
                newState.copy(orders5MonthsAgo = count)
            }
        }.launchIn(viewModelScope)
        orderUseCases.getCountOfOrdersMonth(pair = _uiState.value.month_year[3]).onEach { count ->
            // month_year[3].ordersCount = count
            _uiState.update { newState ->
                newState.copy(orders4MonthsAgo = count)
            }
        }.launchIn(viewModelScope)
        orderUseCases.getCountOfOrdersMonth(pair = _uiState.value.month_year[2]).onEach { count ->
            // month_year[2].ordersCount = count
            _uiState.update { newState ->
                newState.copy(orders3MonthsAgo = count)
            }
        }.launchIn(viewModelScope)
        orderUseCases.getCountOfOrdersMonth(pair = _uiState.value.month_year[1]).onEach { count ->
            // month_year[1].ordersCount = count
            _uiState.update { newState ->
                newState.copy(ordersToLastMonth = count)
            }
        }.launchIn(viewModelScope)
        orderUseCases.getCountOfOrdersMonth(pair = _uiState.value.month_year[0]).onEach { count ->
            // month_year[0].ordersCount = count
            _uiState.update { newState ->
                newState.copy(ordersByThisMonth = count)
            }
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
        // getOrderMonth()
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
                month_year = list,
            )
        }
        list.forEach {
            Log.d("AAAAr", "${it.month} ${it.year}")
        }
    }

//    private fun addLabelForAxis() {
//        val totalCount = mutableListOf<Point>()
//        val monthlyCount = mutableListOf<Point>()
//
////        val ordersPerMonthLabel = mutableListOf<String>()
////        val monthlyOrdersLabel = mutableListOf<Float>()
//
//        repeat(5) { index ->
//            pointsDataPerMonth.add(
//                Point(
//                    index.toFloat(),
//                    uiState.value.ordersPerMonth[index].count.toFloat(),
//                ),
//            )
//            ordersPerMonthLabel.add(uiState.value.ordersPerMonth[index].month.substring(0, 3))
//        }
//        repeat(uiState.value.monthlyOrders.size) { index ->
//            pointsDataMonthly.add(
//                Point(
//                    uiState.value.monthlyOrders[index].month.toFloat(),
//                    uiState.value.monthlyOrders[index].count.toFloat(),
//                ),
//            )
//            monthlyOrdersLabel.add(uiState.value.monthlyOrders[index].count.toFloat())
//        }
//
//        setMaxMin()
//
//        _uiState.update { newState ->
//            newState.copy(
//                pointsDataPerMonth = pointsDataPerMonth,
//                pointsDataMonthly = pointsDataMonthly,
//                ordersPerMonthLabel = ordersPerMonthLabel,
//                monthlyOrdersLabel = monthlyOrdersLabel,
//            )
//        }
//    }
//
//    private fun setMaxMin() {
//        uiState.value.pointsDataMonthly.onEach {
//            maxBy = max(it.y, maxBy)
//            minBy = min(it.y, minBy)
//        }
//    }
}
