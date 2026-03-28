package com.ncrdesarrollo.apicolombianativo.regions.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RegionsApiService {

    @GET("Region")
    suspend fun getRegions(): Response<List<RegionDto>>

    @GET("Region/{id}")
    suspend fun getRegionById(@Path("id") id: Int): Response<RegionDto>
}