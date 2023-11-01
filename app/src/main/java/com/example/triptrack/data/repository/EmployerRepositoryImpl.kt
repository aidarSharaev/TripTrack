package com.example.triptrack.data.repository

import com.example.triptrack.data.local.dao.EmployerDao
import com.example.triptrack.domain.repository.EmployerRepository
import com.example.triptrack.model.Employer
import kotlinx.coroutines.flow.Flow

class EmployerRepositoryImpl(
  private val employerDao: EmployerDao
): EmployerRepository {

  override fun getAllEmployers(): Flow<List<Employer>> {
    return employerDao.getAllEmployers()
  }

  override suspend fun getEmployerById(description: String): Employer {
    return employerDao.getEmployerById(description)
  }

  override suspend fun insertEmployer(employer: Employer) {
    employerDao.insertEmployer(employer)
  }

  override suspend fun deleteEmployer(employer: Employer) {
    employerDao.deleteEmployer(employer)
  }

}