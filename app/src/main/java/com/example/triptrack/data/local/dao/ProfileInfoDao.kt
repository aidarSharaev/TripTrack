package com.example.triptrack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.triptrack.model.ProfileInfo

@Dao
interface ProfileInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfileInfo(profileInfo: ProfileInfo)

    @Query("select * from PROFILE_INFORMATION_TABLE")
    suspend fun getProfileInfo(): ProfileInfo
}
