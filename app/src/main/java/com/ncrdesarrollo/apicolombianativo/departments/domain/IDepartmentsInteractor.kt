package com.ncrdesarrollo.apicolombianativo.departments.domain

import com.ncrdesarrollo.apicolombianativo.departments.ui.model.DepartmentModel
import kotlinx.coroutines.flow.Flow

interface IDepartmentsInteractor {

    fun getDepartments(): Flow<Result<List<DepartmentModel>>>
    suspend fun getDepartmentById(id: Int): Result<DepartmentModel?>
}