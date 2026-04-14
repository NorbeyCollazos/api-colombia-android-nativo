package com.ncrdesarrollo.apicolombianativo.touristicAttraction.data

import com.ncrdesarrollo.apicolombianativo.touristicAttraction.domain.ITouristicAttractionsRepository
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.ui.model.TouristicAttractionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class TouristicAttractionsRepository @Inject constructor(private val dataSource: ITouristicAttractionDataSource) :
    ITouristicAttractionsRepository {
    override fun getTouristicAttractions(): Flow<Result<List<TouristicAttractionModel>>> {

        return dataSource.getTouristicAttractionsLocal()
            .map { localTouristicAttractions ->
                Result.success(localTouristicAttractions.map { it.toDomain() })
            }
            .onStart {
                val responseApi = dataSource.getTouristicAttractions()
                    .mapCatching {
                        it.map { touristicDto ->
                            touristicDto.toDomain()
                        }
                    }
                responseApi.getOrNull()?.let { touristic ->
                    dataSource.upsertTouristicAttractions(touristic.map { it.toEntity() })
                }
            }
            .catch { e ->
                emit(Result.failure(exception = e))
            }
    }

    override suspend fun getTouristicAttractionById(id: Int): Result<TouristicAttractionModel?> =
        dataSource.getTouristicAttractionById(id)
            .mapCatching {
                it?.toDomain()
            }

}