package com.example.triptrack.domain.usecaces.local_data.employer

import com.example.triptrack.data.local.dao.EmployerDao
import com.example.triptrack.model.Employer

class GetEmployerById(
  private val employerDao: EmployerDao
) {

  suspend operator fun invoke(description: String) : Employer {
    return employerDao.getEmployerById(description = description)
  }

}