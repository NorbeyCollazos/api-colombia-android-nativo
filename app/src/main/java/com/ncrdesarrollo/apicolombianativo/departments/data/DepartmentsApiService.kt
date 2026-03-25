package com.ncrdesarrollo.apicolombianativo.departments.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DepartmentsApiService {

    @GET("Department")
    suspend fun getDepartments(): Response<List<DepartmentDto>>

    @GET("Department/{id}")
    suspend fun getDepartmentById(@Path("id") id: Int): Response<DepartmentDto>
}