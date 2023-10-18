package com.example.triptrack.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.triptrack.model.Employer
import com.example.triptrack.utils.Constants.EMPLOYER_TABLE

@Dao
interface EmployerDao {

  @Query("select * from $EMPLOYER_TABLE")
  suspend fun getAllEmployer(): List<Employer>

  @Query("select * from $EMPLOYER_TABLE where id = :id")
  suspend fun getEmployerById(id: Int): Employer

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertEmployer(employer: Employer)

  @Delete
  suspend fun deleteEmployer(employer: Employer)
}