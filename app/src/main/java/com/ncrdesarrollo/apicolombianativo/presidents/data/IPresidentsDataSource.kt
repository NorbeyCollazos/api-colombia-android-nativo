package com.ncrdesarrollo.apicolombianativo.presidents.data

import com.ncrdesarrollo.apicolombianativo.presidents.data.local.PresidentEntity
import kotlinx.coroutines.flow.Flow

interface IPresidentsDataSource {

    suspend fun getPresidents(): Result<List<PresidentDto>>
    suspend fun upsertPresidents(departments: List<PresidentEntity>)
    fun getPresidentsLocal(): Flow<List<PresidentEntity>>
    suspend fun getPresidentById(id: Int): Result<PresidentEntity?>
}


