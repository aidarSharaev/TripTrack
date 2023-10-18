package com.example.triptrack.di

import android.content.Context
import androidx.room.Room
import com.example.triptrack.data.local.dao.TripTrackDatabase
import com.example.triptrack.utils.Constants.TRIP_TRACK_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideDatabase(
    @ApplicationContext context: Context
  ) : TripTrackDatabase {
    return Room.databaseBuilder(
      context,
      TripTrackDatabase::class.java,
      TRIP_TRACK_DATABASE
    ).build()
  }

}