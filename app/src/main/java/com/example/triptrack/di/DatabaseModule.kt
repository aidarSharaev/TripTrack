package com.example.triptrack.di

import android.content.Context
import androidx.room.Room
import com.example.triptrack.data.local.dao.BankDataDao
import com.example.triptrack.data.local.dao.EmployerDao
import com.example.triptrack.data.local.dao.OrderDao
import com.example.triptrack.data.local.dao.ProfileInfoDao
import com.example.triptrack.data.local.dao.TripTrackDatabase
import com.example.triptrack.data.repository.OrderRepositoryImpl
import com.example.triptrack.domain.repository.OrderRepository
import com.example.triptrack.domain.usecaces.local_data.bank_data.BankDataUseCases
import com.example.triptrack.domain.usecaces.local_data.bank_data.InsertBankData
import com.example.triptrack.domain.usecaces.local_data.bank_data.UpdateBankData
import com.example.triptrack.domain.usecaces.local_data.employer.DeleteEmployer
import com.example.triptrack.domain.usecaces.local_data.employer.EmployerUseCases
import com.example.triptrack.domain.usecaces.local_data.employer.GetEmployerById
import com.example.triptrack.domain.usecaces.local_data.employer.GetEmployers
import com.example.triptrack.domain.usecaces.local_data.employer.InsertEmployer
import com.example.triptrack.domain.usecaces.local_data.order.DeleteOrder
import com.example.triptrack.domain.usecaces.local_data.order.GetOrderById
import com.example.triptrack.domain.usecaces.local_data.order.GetOrderPaging
import com.example.triptrack.domain.usecaces.local_data.order.InsertOrder
import com.example.triptrack.domain.usecaces.local_data.order.OrderCount
import com.example.triptrack.domain.usecaces.local_data.order.OrderUseCases
import com.example.triptrack.domain.usecaces.local_data.profile_info.GetProfile
import com.example.triptrack.domain.usecaces.local_data.profile_info.InsertProfile
import com.example.triptrack.domain.usecaces.local_data.profile_info.ProfileUseCases
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
  fun providesOrderDao(tripTrackDatabase: TripTrackDatabase): OrderDao =
    tripTrackDatabase.orderDao()

  @Provides
  @Singleton
  fun providesEmployerDao(tripTrackDatabase: TripTrackDatabase): EmployerDao =
    tripTrackDatabase.employerDao()

  @Provides
  @Singleton
  fun providesProfileInfoDao(tripTrackDatabase: TripTrackDatabase): ProfileInfoDao =
    tripTrackDatabase.profileInfoDao()

  @Provides
  @Singleton
  fun providesBankDataDao(tripTrackDatabase: TripTrackDatabase): BankDataDao =
    tripTrackDatabase.bankDataDao()

  @Provides
  @Singleton
  fun provideOrderRepository(
    orderDao: OrderDao
  ): OrderRepository {
    return OrderRepositoryImpl(orderDao)
  }

  @Provides
  @Singleton
  fun provideProfileUseCases(
    profileInfoDao: ProfileInfoDao
  ): ProfileUseCases {
    return ProfileUseCases(
      getProfile = GetProfile(profileInfoDao = profileInfoDao),
      insertProfile = InsertProfile(profileInfoDao = profileInfoDao)
    )
  }

  @Provides
  @Singleton
  fun provideOrderUseCases(
    orderDao: OrderDao,
    orderRepository: OrderRepository
  ): OrderUseCases {
    return OrderUseCases(
      deleteOrder = DeleteOrder(orderDao = orderDao),
      getOrderById = GetOrderById(orderDao = orderDao),
      getOrderPaging = GetOrderPaging(orderRepository = orderRepository),
      insertOrder = InsertOrder(orderDao = orderDao),
      orderCount = OrderCount(orderDao = orderDao)
    )
  }

  @Provides
  @Singleton
  fun provideEmployerUseCases(
    employerDao: EmployerDao,
  ): EmployerUseCases {
    return EmployerUseCases(
      deleteEmployer = DeleteEmployer(employerDao = employerDao),
      getEmployerById = GetEmployerById(employerDao = employerDao),
      getEmployers = GetEmployers(employerDao = employerDao),
      insertEmployer = InsertEmployer(employerDao = employerDao)
    )
  }

  @Provides
  @Singleton
  fun provideBankDataUseCases(
    bankDataDao: BankDataDao
  ): BankDataUseCases {
    return BankDataUseCases(
      updateBankData = UpdateBankData(bankDataDao = bankDataDao),
      insertBankData = InsertBankData(bankDataDao = bankDataDao)
    )
  }

}