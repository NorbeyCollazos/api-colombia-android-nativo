package com.ncrdesarrollo.apicolombianativo.touristicAttraction.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TouristicAttractionsApiService {

    @GET("TouristicAttraction")
    suspend fun getTouristicAttractions(): Response<List<TouristicAttractionDto>>

    @GET("TouristicAttraction/{id}")
    suspend fun getTouristicAttractionById(@Path("id") id: Int): Response<TouristicAttractionDto>
}