package com.example.triptrack.domain.usecaces.local_data.bank_data

import com.example.triptrack.data.local.dao.BankDataDao
import com.example.triptrack.model.BankData
import kotlinx.coroutines.flow.Flow

class GetBankData(
    private val bankDataDao: BankDataDao
) {
    operator fun invoke(): Flow<BankData?> {
        return bankDataDao.selectBankData()
    }
}