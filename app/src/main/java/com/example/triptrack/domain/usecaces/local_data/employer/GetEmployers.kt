package com.example.triptrack.domain.usecaces.local_data.employer

import com.example.triptrack.data.local.dao.EmployerDao
import com.example.triptrack.model.Employer
import javax.inject.Inject

class GetEmployers @Inject constructor(
  private val employerDao: EmployerDao
) {

  suspend operator fun invoke(): List<Employer> {
    return employerDao.getAllEmployers()
  }

}