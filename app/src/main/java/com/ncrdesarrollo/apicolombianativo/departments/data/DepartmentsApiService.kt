package com.ncrdesarrollo.apicolombianativo.departments.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DepartmentsApiService {

    @GET("Department")
    suspend fun getDepartments(): Response<ResponseBody>

    @GET("Department/{id}")
    suspend fun getDepartmentById(@Path("id") id: Int): Response<ResponseBody>
}