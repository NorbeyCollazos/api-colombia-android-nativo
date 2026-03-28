package com.ncrdesarrollo.apicolombianativo.regions.data

import com.ncrdesarrollo.apicolombianativo.regions.data.RegionDto
import com.ncrdesarrollo.apicolombianativo.regions.data.local.RegionsEntity
import kotlinx.coroutines.flow.Flow

interface IRegionsDataSource {

    suspend fun getRegions(): Result<List<RegionDto>>
    suspend fun upsertRegions(departments: List<RegionsEntity>)
    fun getRegionsLocal(): Flow<List<RegionsEntity>>
    suspend fun getRegionById(id: Int): Result<RegionsEntity?>
}


