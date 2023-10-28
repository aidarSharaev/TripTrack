package com.example.triptrack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.triptrack.utils.Constants.PROFILE_INFORMATION_TABLE

@Entity(tableName = PROFILE_INFORMATION_TABLE,
  foreignKeys = [ForeignKey(
    entity = BankData::class,
    childColumns = ["recipientId"],
    parentColumns = ["id"],
    onUpdate = ForeignKey.CASCADE,
    onDelete = ForeignKey.CASCADE
  )])
data class ProfileInfo(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val firstName: String,
  val lastName: String,
  @ColumnInfo(index = true)
  val recipientId: Int,
)
