package com.ncrdesarrollo.apicolombianativo.departments.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartmentsDao {

    @Upsert
    suspend fun upsertDepartment(departments: List<DepartmentEntity>)

    @Query("SELECT * FROM departments")
    fun getDepartments(): Flow<List<DepartmentEntity>>

    @Query("SELECT * FROM departments WHERE id = :departmentId LIMIT 1")
    suspend fun getDepartmentById(departmentId: Int): DepartmentEntity?
}