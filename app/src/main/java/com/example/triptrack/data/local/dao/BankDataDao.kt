package com.example.triptrack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.triptrack.model.BankData
import com.example.triptrack.utils.Constants.BANK_DATA_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface BankDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBankData(bankData: BankData)

    @Query("select * from $BANK_DATA_TABLE where id = 0")
    fun selectBankData(): Flow<BankData?>
}
