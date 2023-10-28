package com.example.triptrack.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.triptrack.model.Order
import com.example.triptrack.utils.Constants.ORDER_TABLE

@Dao
interface OrderDao {
//todo desc
  @Query(value = "select * from $ORDER_TABLE order by id asc")
  fun loadAllOrdersPaged(): PagingSource<Int, Order>

  @Query(value = "select * from $ORDER_TABLE where id = :id")
  suspend fun getOrderById(id: Int): Order

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOrder(order: Order)

  @Delete
  suspend fun deleteOrderById(order: Order)

}