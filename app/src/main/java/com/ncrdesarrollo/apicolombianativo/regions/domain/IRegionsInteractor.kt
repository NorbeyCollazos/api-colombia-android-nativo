package com.ncrdesarrollo.apicolombianativo.regions.domain

import com.ncrdesarrollo.apicolombianativo.departments.ui.model.RegionModel
import kotlinx.coroutines.flow.Flow

interface IRegionsInteractor {

    fun getRegions(): Flow<Result<List<RegionModel>>>
    suspend fun getRegionById(id: Int): Result<RegionModel?>
}