package com.example.triptrack.domain.usecaces.local_data.profile_info

import com.example.triptrack.data.local.dao.ProfileInfoDao
import com.example.triptrack.model.ProfileInfo

class InsertProfile(
    private val profileInfoDao: ProfileInfoDao,
) {

    suspend operator fun invoke(profileInfo: ProfileInfo) {
        profileInfoDao.insertProfileInfo(profileInfo = profileInfo)
    }
}
