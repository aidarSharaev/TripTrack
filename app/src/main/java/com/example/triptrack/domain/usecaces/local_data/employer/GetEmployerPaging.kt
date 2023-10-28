package com.example.triptrack.domain.usecaces.local_data.employer

import androidx.paging.PagingData
import com.example.triptrack.domain.remote.LocalDataRepository
import com.example.triptrack.model.Employer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEmployerPaging @Inject constructor(
  private val localDataRepository: LocalDataRepository
) {

  operator fun invoke() : Flow<PagingData<Employer>> {
    return localDataRepository.getEmployer()
  }
}