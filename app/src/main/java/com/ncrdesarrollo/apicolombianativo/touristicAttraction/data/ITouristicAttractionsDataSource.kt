package com.ncrdesarrollo.apicolombianativo.touristicAttraction.data

import com.ncrdesarrollo.apicolombianativo.touristicAttraction.data.local.TouristicAttractionEntity
import kotlinx.coroutines.flow.Flow

interface ITouristicAttractionDataSource {

    suspend fun getTouristicAttractions(): Result<List<TouristicAttractionDto>>
    suspend fun upsertTouristicAttractions(departments: List<TouristicAttractionEntity>)
    fun getTouristicAttractionsLocal(): Flow<List<TouristicAttractionEntity>>
    suspend fun getTouristicAttractionById(id: Int): Result<TouristicAttractionEntity?>
}


