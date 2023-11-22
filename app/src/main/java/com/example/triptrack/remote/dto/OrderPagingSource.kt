package com.example.triptrack.remote.dto

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.triptrack.data.local.dao.OrderDao
import com.example.triptrack.model.Order
import kotlinx.coroutines.flow.onEach

class OrdersPagingSource(
    private val orderDao: OrderDao,
) : PagingSource<Int, Order>() {
    override fun getRefreshKey(state: PagingState<Int, Order>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Order> {
        var count: Int = 0
        orderDao.getOrderCount().onEach {
            count = it
        }
        var currentCount = 0
        val page = params.key ?: 1
        return try {
            val orders = orderDao.loadAllOrdersPaged()
            currentCount += orders.size
            LoadResult.Page(
                data = orders,
                prevKey = null,
                nextKey = if (count == currentCount) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e,
            )
        }
    }
}
