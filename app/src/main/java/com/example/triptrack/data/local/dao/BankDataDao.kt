package com.example.triptrack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.triptrack.model.BankData

@Dao
interface BankDataDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertBankData(bankData: BankData)

  @Update
  suspend fun updateBankData(bankData: BankData)

}