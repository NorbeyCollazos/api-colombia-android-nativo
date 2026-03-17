package com.ncrdesarrollo.apicolombianativo.info.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class InfoDataSource @Inject constructor(private val infoApiService: InfoApiService) : IInfoDataSource {
    override suspend fun getInfo(): Response<ResponseBody> {
        return withContext(Dispatchers.IO) {infoApiService.getInfo()}
    }
}