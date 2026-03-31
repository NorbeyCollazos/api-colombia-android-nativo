package com.ncrdesarrollo.apicolombianativo.presidents.data

import com.ncrdesarrollo.apicolombianativo.presidents.data.local.PresidentEntity
import com.ncrdesarrollo.apicolombianativo.presidents.data.local.PresidentsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PresidentsDataSource @Inject constructor(
    private val presidentsApiService: PresidentsApiService,
    private val presidentDao: PresidentsDao
) :
    IPresidentsDataSource {
    override suspend fun getPresidents(): Result<List<PresidentDto>> = runCatching {
        val response = presidentsApiService.getPresidents()
        if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            throw Exception("Error: ${response.code()}")
        }
    }

    override suspend fun upsertPresidents(departments: List<PresidentEntity>) {
        presidentDao.upsertPresident(departments)
    }

    override fun getPresidentsLocal(): Flow<List<PresidentEntity>> =
        presidentDao.getPresidents()

    override suspend fun getPresidentById(id: Int): Result<PresidentEntity?> = runCatching {
        presidentDao.getPresidentById(id)
    }
}