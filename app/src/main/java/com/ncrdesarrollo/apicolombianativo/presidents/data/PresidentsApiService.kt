package com.ncrdesarrollo.apicolombianativo.presidents.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PresidentsApiService {

    @GET("President")
    suspend fun getPresidents(): Response<List<PresidentDto>>

    @GET("President/{id}")
    suspend fun getPresidentById(@Path("id") id: Int): Response<PresidentDto>
}