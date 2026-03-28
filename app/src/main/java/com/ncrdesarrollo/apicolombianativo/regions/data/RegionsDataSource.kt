package com.ncrdesarrollo.apicolombianativo.regions.data

import com.ncrdesarrollo.apicolombianativo.regions.data.local.RegionsDao
import com.ncrdesarrollo.apicolombianativo.regions.data.local.RegionsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegionsDataSource @Inject constructor(
    private val regionsApiService: RegionsApiService,
    private val regionsDao: RegionsDao
) :
    IRegionsDataSource {
    override suspend fun getRegions(): Result<List<RegionDto>> = runCatching {
        val response = regionsApiService.getRegions()
        if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            throw Exception("Error: ${response.code()}")
        }
    }

    override suspend fun upsertRegions(departments: List<RegionsEntity>) {
        regionsDao.upsertRegion(departments)
    }

    override fun getRegionsLocal(): Flow<List<RegionsEntity>> =
        regionsDao.getRegions()

    override suspend fun getRegionById(id: Int): Result<RegionsEntity?> = runCatching {
        regionsDao.getRegionById(id)
    }
}