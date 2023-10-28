package com.example.triptrack.domain.usecaces.app_entry

import com.example.triptrack.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
  private val localUserManager: LocalUserManager
) {

  operator fun invoke(): Flow<Boolean> {
    return localUserManager.readAppEntry()
  }

}

//usecases