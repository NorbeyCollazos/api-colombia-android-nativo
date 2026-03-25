package com.ncrdesarrollo.apicolombianativo.departments.data

import com.ncrdesarrollo.apicolombianativo.departments.data.local.DepartmentEntity
import kotlinx.coroutines.flow.Flow

interface IDepartmentsDataSource {

    suspend fun getDepartments(): Result<List<DepartmentDto>>
    suspend fun upsertDepartments(departments: List<DepartmentEntity>)
    fun getDepartmentsLocal(): Flow<List<DepartmentEntity>>
    suspend fun getDepartmentById(id: Int): Result<DepartmentEntity?>
}


