package com.example.triptrack.domain.usecaces.local_data.employer

import com.example.triptrack.data.local.dao.EmployerDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEmployers @Inject constructor(
    private val employerDao: EmployerDao,
) {

    operator fun invoke(): Flow<List<String>> = flow {
        emit(employerDao.getAllEmployers())
    }
}
