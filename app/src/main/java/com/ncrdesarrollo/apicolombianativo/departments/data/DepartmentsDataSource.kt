package com.ncrdesarrollo.apicolombianativo.departments.data

import okhttp3.ResponseBody
import retrofit2.Response

class DepartmentsDataSource constructor(private val departmentsApiService: DepartmentsApiService): IDepartmentsDataSource {
    override suspend fun getDepartments(): Response<ResponseBody> {
        return departmentsApiService.getDepartments()
    }

    override suspend fun getDepartmentById(id: Int): Response<ResponseBody> {
        return departmentsApiService.getDepartmentById(id)
    }
}