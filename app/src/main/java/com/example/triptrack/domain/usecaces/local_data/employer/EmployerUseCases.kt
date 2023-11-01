package com.example.triptrack.domain.usecaces.local_data.employer

data class EmployerUseCases(
  val deleteEmployer: DeleteEmployer,
  val getEmployers: GetEmployers,
  val getEmployerById: GetEmployerById,
  val insertEmployer: InsertEmployer
)
