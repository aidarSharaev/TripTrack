package com.example.triptrack.domain.usecaces.app_entry

import com.example.triptrack.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager,
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}
