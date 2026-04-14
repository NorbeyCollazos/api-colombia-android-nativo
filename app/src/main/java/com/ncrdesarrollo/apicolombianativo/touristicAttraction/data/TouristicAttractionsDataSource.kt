package com.ncrdesarrollo.apicolombianativo.touristicAttraction.data

import com.ncrdesarrollo.apicolombianativo.touristicAttraction.data.local.TouristicAttractionDao
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.data.local.TouristicAttractionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TouristicAttractionsDataSource @Inject constructor(
    private val touristicAttractionsApiService: TouristicAttractionsApiService,
    private val touristicAttractionDao: TouristicAttractionDao
) :
    ITouristicAttractionDataSource {
    override suspend fun getTouristicAttractions(): Result<List<TouristicAttractionDto>> =
        runCatching {
            val response = touristicAttractionsApiService.getTouristicAttractions()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                throw Exception("Error: ${response.code()}")
            }
        }

    override suspend fun upsertTouristicAttractions(touristicAttraction: List<TouristicAttractionEntity>) {
        touristicAttractionDao.upsertTouristicAttraction(touristicAttraction)
    }

    override fun getTouristicAttractionsLocal(): Flow<List<TouristicAttractionEntity>> =
        touristicAttractionDao.getTouristicAttractions()

    override suspend fun getTouristicAttractionById(id: Int): Result<TouristicAttractionEntity?> =
        runCatching {
            touristicAttractionDao.getTouristicAttractionById(id)
        }
}