package com.example.triptrack.data.repository

import com.example.triptrack.data.local.dao.BankDataDao
import com.example.triptrack.domain.repository.BankDataRepository
import com.example.triptrack.model.BankData

class BankDataRepositoryImpl(
  private val bankDataDao: BankDataDao
): BankDataRepository {

  override suspend fun insertBankData(bankData: BankData) {
    bankDataDao.insertBankData(bankData = bankData)
  }

  override suspend fun updateBankData(bankData: BankData) {
    bankDataDao.updateBankData(bankData = bankData)
  }

}