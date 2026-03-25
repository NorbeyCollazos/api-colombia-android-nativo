package com.ncrdesarrollo.apicolombianativo.departments.domain

import com.ncrdesarrollo.apicolombianativo.departments.ui.model.DepartmentModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DepartmentsInteractor @Inject constructor(private val repository: IDepartmentsRepository): IDepartmentsInteractor {
    override fun getDepartments(): Flow<Result<List<DepartmentModel>>> {
        return repository.getDepartments()
    }

    override suspend fun getDepartmentById(id: Int): Result<DepartmentModel?> {
        return repository.getDepartmentById(id)
    }
}