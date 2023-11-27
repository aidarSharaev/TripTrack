package com.example.triptrack.domain.usecaces.local_data.profile_info

import com.example.triptrack.data.local.dao.ProfileInfoDao
import com.example.triptrack.model.ProfileInfo
import kotlinx.coroutines.flow.Flow

class GetProfile(
    private val profileInfoDao: ProfileInfoDao,
) {

    operator fun invoke(): Flow<ProfileInfo?> {
        return profileInfoDao.getProfileInfo()
    }
}
