package com.ncrdesarrollo.apicolombianativo.info.data

import com.ncrdesarrollo.apicolombianativo.info.data.local.InfoEntity
import kotlinx.coroutines.flow.Flow

interface IInfoDataSource {

    suspend fun getInfo(): Result<InfoDto>
    suspend fun saveLocalInfo(infoEntity: InfoEntity)
    fun getInfoLocal(): Flow<InfoEntity>
}


