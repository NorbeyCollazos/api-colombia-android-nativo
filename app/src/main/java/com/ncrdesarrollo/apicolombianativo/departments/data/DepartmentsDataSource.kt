package com.ncrdesarrollo.apicolombianativo.departments.data

import com.ncrdesarrollo.apicolombianativo.departments.data.local.DepartmentEntity
import com.ncrdesarrollo.apicolombianativo.departments.data.local.DepartmentsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DepartmentsDataSource @Inject constructor(
    private val departmentsApiService: DepartmentsApiService,
    private val departmentsDao: DepartmentsDao
) :
    IDepartmentsDataSource {
    override suspend fun getDepartments(): Result<List<DepartmentDto>> = runCatching {
        val response = departmentsApiService.getDepartments()
        if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            throw Exception("Error: ${response.code()}")
        }
    }

    override suspend fun upsertDepartments(departments: List<DepartmentEntity>) {
        departmentsDao.upsertDepartment(departments)
    }

    override fun getDepartmentsLocal(): Flow<List<DepartmentEntity>> =
        departmentsDao.getDepartments()

    override suspend fun getDepartmentById(id: Int): Result<DepartmentEntity?> = runCatching {
        departmentsDao.getDepartmentById(id)
    }
}