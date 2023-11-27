package com.example.triptrack.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.triptrack.model.Order
import com.example.triptrack.utils.Constants.ORDER_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    // todo desc
    @Query(value = "select * from $ORDER_TABLE order by id asc")
    fun loadAllOrdersPaged(): PagingSource<Int, Order>

    @Query(value = "select * from $ORDER_TABLE where id = :id")
    suspend fun getOrderById(id: Int): Order

    @Query(value = "select count(*) from $ORDER_TABLE")
    fun getOrderCount(): Flow<Int>

    @Query(value = "select count(*) from $ORDER_TABLE where date between '01/01/1900' and '01/' || :month || '/' || :year || '%'")
    fun getAbsolutelyAllOrdersExceptForLast5Months(month: String, year: String): Flow<Int>

    @Query(value = "select count(*) from $ORDER_TABLE where date like '%/' || :month || '/' || :year || '%'")
    fun getCountOfOrdersByMonth(month: String, year: String): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)

    @Delete
    suspend fun deleteOrderById(order: Order)
}
