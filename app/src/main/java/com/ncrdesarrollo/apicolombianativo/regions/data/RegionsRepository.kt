package com.ncrdesarrollo.apicolombianativo.regions.data

import com.ncrdesarrollo.apicolombianativo.departments.ui.model.RegionModel
import com.ncrdesarrollo.apicolombianativo.regions.domain.IRegionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RegionsRepository @Inject constructor(private val dataSource: IRegionsDataSource) :
    IRegionsRepository {
    override fun getRegions(): Flow<Result<List<RegionModel>>> {

        return dataSource.getRegionsLocal()
            .map { localRegions ->
                Result.success(localRegions.map { it.toDomain() })
            }
            .onStart {
                val responseApi = dataSource.getRegions()
                    .mapCatching {
                        it.map { departmentDto ->
                            departmentDto.toDomain()
                        }
                    }
                responseApi.getOrNull()?.let { departments ->
                    dataSource.upsertRegions(departments.map { it.toEntity() })
                }
            }
            .catch { e ->
                emit(Result.failure(exception = e))
            }
    }

    override suspend fun getRegionById(id: Int): Result<RegionModel?> =
        dataSource.getRegionById(id)
            .mapCatching {
                it?.toDomain()
            }

}