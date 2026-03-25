package com.ncrdesarrollo.apicolombianativo.info.data

import retrofit2.Response
import retrofit2.http.GET

interface InfoApiService {

    @GET("Country/Colombia")
    suspend fun getInfo(): Response<InfoDto>
}