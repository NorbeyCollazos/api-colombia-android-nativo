package com.ncrdesarrollo.apicolombianativo.departments.data

import okhttp3.ResponseBody
import retrofit2.Response

interface IDepartmentsDataSource {

    suspend fun getDepartments(): Response<ResponseBody>
    suspend fun getDepartmentById(id: Int): Response<ResponseBody>
}


