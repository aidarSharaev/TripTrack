package com.example.triptrack.domain.usecaces.local_data.employer

import com.example.triptrack.data.local.dao.EmployerDao
import com.example.triptrack.model.Employer
import javax.inject.Inject

class DeleteEmployer @Inject constructor(
  private val employerDao: EmployerDao
) {

  suspend operator fun invoke(employer: Employer) {
    employerDao.deleteEmployer(employer)
  }
}