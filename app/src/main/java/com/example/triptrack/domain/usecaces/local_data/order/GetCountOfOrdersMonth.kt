package com.example.triptrack.domain.usecaces.local_data.order

import com.example.triptrack.data.local.dao.OrderDao
import com.example.triptrack.screen.statistics.CustomPair
import kotlinx.coroutines.flow.Flow

class GetCountOfOrdersMonth(
    private val orderDao: OrderDao,
) {

    operator fun invoke(pair: CustomPair): Flow<Int> {
        return (orderDao.getCountOfOrdersByMonth(month = pair.month, year = pair.year))
    }
}
