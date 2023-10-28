package com.example.triptrack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.triptrack.utils.Constants.ORDER_TABLE

@Entity(tableName = ORDER_TABLE,
  foreignKeys = [
    ForeignKey(
      entity = Employer::class,
      childColumns = ["employerDescription"],
      parentColumns = ["description"],
      onDelete = ForeignKey.SET_DEFAULT,
      onUpdate = ForeignKey.SET_DEFAULT
    )
  ]
)
data class Order(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  @ColumnInfo(index = true)
  val employerDescription: String? = null,
  val route: String,
  val payment: Boolean,
  val tax: Boolean,
  val date: String,
  val income: Int,
  val wastes: Int = 0,
  val profit: Int,
)
