package com.ncrdesarrollo.apicolombianativo.departments.domain

import com.ncrdesarrollo.apicolombianativo.departments.ui.model.DepartmentModel

interface IDepartmentsRepository {

    suspend fun getDepartments(): List<DepartmentModel>
    suspend fun getDepartmentById(id: Int): DepartmentModel
}