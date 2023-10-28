package com.example.triptrack.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.triptrack.model.Employer
import com.example.triptrack.model.Order

@Database(entities = [
  Employer::class,
  //ProfileInfo::class,
  //BankData::class,
  Order::class],
  version = 1,
  exportSchema = false)
abstract class TripTrackDatabase: RoomDatabase(){

  //abstract fun bankDataDao(): BankDataDao
  abstract fun employerDao(): EmployerDao
  abstract fun orderDao(): OrderDao
 // abstract fun profileInfoDao(): ProfileInfoDao

}