package com.example.triptrack.di

import android.content.Context
import androidx.room.Room
import com.example.triptrack.data.local.dao.EmployerDao
import com.example.triptrack.data.local.dao.OrderDao
import com.example.triptrack.data.local.dao.TripTrackDatabase
import com.example.triptrack.domain.remote.LocalDataRepository
import com.example.triptrack.domain.usecaces.local_data.LocalDataUseCases
import com.example.triptrack.domain.usecaces.local_data.employer.GetEmployerPaging
import com.example.triptrack.domain.usecaces.local_data.order.GetOrderPaging
import com.example.triptrack.domain.usecaces.local_data.order.InsertOrder
import com.example.triptrack.remote.LocalDataRepositoryImpl
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
  ): TripTrackDatabase {
    return Room.databaseBuilder(
      context,
      TripTrackDatabase::class.java,
      TRIP_TRACK_DATABASE
    ).build()
  }

  @Provides
  @Singleton
  fun provideLocalDataRepository(
    orderDao: OrderDao,
    employerDao: EmployerDao
  ): LocalDataRepository = LocalDataRepositoryImpl(orderDao, employerDao)

  @Provides
  @Singleton
  fun providesOrderDao(tripTrackDatabase: TripTrackDatabase): OrderDao =
    tripTrackDatabase.orderDao()

  @Provides
  @Singleton
  fun providesEmployerDao(tripTrackDatabase: TripTrackDatabase): EmployerDao =
    tripTrackDatabase.employerDao()

  @Provides
  @Singleton
  fun provideLocalDataUseCases(
    localDataRepository: LocalDataRepository,
    orderDao: OrderDao
  ): LocalDataUseCases {

    return LocalDataUseCases(
      getOrder = GetOrderPaging(localDataRepository = localDataRepository),
      getEmployer = GetEmployerPaging(localDataRepository = localDataRepository),
      insertOrder = InsertOrder(orderDao = orderDao)
    )
  }

}