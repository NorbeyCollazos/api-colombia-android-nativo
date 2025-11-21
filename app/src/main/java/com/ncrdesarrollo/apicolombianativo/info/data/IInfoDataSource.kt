package com.ncrdesarrollo.apicolombianativo.info.data

import okhttp3.ResponseBody
import retrofit2.Response

interface IInfoDataSource {

    suspend fun getInfo(): Response<ResponseBody>
}


