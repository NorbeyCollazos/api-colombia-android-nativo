package com.ncrdesarrollo.apicolombianativo.info.data

import com.ncrdesarrollo.apicolombianativo.info.data.local.InfoDao
import com.ncrdesarrollo.apicolombianativo.info.data.local.InfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InfoDataSource @Inject constructor(
    private val infoApiService: InfoApiService,
    private val infoDao: InfoDao
) : IInfoDataSource {
    override suspend fun getInfo(): Result<InfoDto> = runCatching {
        val response = infoApiService.getInfo()
        if (response.isSuccessful) {
            response.body() ?: throw Exception("Error al obtener los datos")
        } else {
            throw Exception("${response.code()}")
        }
    }

    override suspend fun saveLocalInfo(infoEntity: InfoEntity) {
        infoDao.upsertInfo(infoEntity)
    }

    override fun getInfoLocal(): Flow<InfoEntity> {
        return infoDao.getInfo()
    }
}