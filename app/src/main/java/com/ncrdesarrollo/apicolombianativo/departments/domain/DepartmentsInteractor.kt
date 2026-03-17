package com.ncrdesarrollo.apicolombianativo.departments.domain

import com.ncrdesarrollo.apicolombianativo.departments.ui.model.DepartmentModel
import javax.inject.Inject

class DepartmentsInteractor @Inject constructor(private val repository: IDepartmentsRepository): IDepartmentsInteractor {
    override suspend fun getDepartments(): List<DepartmentModel> {
        return repository.getDepartments()
    }

    override suspend fun getDepartmentById(id: Int): DepartmentModel {
        return repository.getDepartmentById(id)
    }
}