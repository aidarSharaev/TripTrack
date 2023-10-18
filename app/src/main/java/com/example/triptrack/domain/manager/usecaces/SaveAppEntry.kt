package com.example.triptrack.domain.manager.usecaces

import com.example.triptrack.domain.manager.LocalUserManager

class SaveAppEntry(
  private val localUserManager: LocalUserManager
  ) {

  suspend operator fun invoke() {
    localUserManager.saveAppEntry()
  }

}

