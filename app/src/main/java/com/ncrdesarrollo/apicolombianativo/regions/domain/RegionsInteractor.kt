package com.ncrdesarrollo.apicolombianativo.regions.domain

import com.ncrdesarrollo.apicolombianativo.departments.ui.model.RegionModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegionsInteractor @Inject constructor(private val repository: IRegionsRepository):
    IRegionsInteractor {
    override fun getRegions(): Flow<Result<List<RegionModel>>> {
        return repository.getRegions()
    }

    override suspend fun getRegionById(id: Int): Result<RegionModel?> {
        return repository.getRegionById(id)
    }
}