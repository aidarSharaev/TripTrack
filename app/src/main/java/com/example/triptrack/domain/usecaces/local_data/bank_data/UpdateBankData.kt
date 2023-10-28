package com.example.triptrack.domain.usecaces.local_data.bank_data

import com.example.triptrack.data.local.dao.BankDataDao
import com.example.triptrack.model.BankData
import javax.inject.Inject

class UpdateBankData @Inject constructor(
  private val bankDataDao: BankDataDao
) {

  suspend operator fun invoke(bankData: BankData) {
    bankDataDao.updateBankData(bankData)
  }

}