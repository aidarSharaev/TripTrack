package com.example.triptrack.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.triptrack.utils.Constants.PROFILE_INFORMATION_TABLE

@Entity(tableName = PROFILE_INFORMATION_TABLE)
data class ProfileInfo(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0, // айдиха
    val firstName: String, // имя
    val lastName: String, // фамилия
)
