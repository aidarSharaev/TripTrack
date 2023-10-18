package com.example.triptrack.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.triptrack.model.Order
import com.example.triptrack.utils.Constants.ORDER_TABLE

@Dao
interface OrderDao {

  @Query("select * from $ORDER_TABLE")
  suspend fun getAllOrder(): List<Order>

  @Query("select * from $ORDER_TABLE where id = :id")
  suspend fun getOrderById(id: Int): Order

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOrder(order: Order)

  @Delete
  suspend fun deleteOrderById(order: Order)

}