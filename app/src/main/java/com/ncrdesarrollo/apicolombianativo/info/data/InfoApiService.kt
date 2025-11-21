package com.ncrdesarrollo.apicolombianativo.info.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface InfoApiService {

    @GET("Country/Colombia")
    suspend fun getInfo(): Response<ResponseBody>
}