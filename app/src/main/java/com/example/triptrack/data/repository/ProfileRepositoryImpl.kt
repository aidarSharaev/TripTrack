package com.example.triptrack.data.repository

import com.example.triptrack.data.local.dao.ProfileInfoDao
import com.example.triptrack.domain.repository.ProfileRepository
import com.example.triptrack.model.ProfileInfo

class ProfileRepositoryImpl (
  private val profileInfoDao: ProfileInfoDao
): ProfileRepository {

  override suspend fun insertProfileInfo(profileInfo: ProfileInfo) {
    profileInfoDao.insertProfileInfo(profileInfo = profileInfo)
  }

  override suspend fun getProfileInfo(): ProfileInfo {
    return profileInfoDao.getProfileInfo()
  }

}