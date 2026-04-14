package com.ncrdesarrollo.apicolombianativo.touristicAttraction.domain

import com.ncrdesarrollo.apicolombianativo.touristicAttraction.ui.model.TouristicAttractionModel
import kotlinx.coroutines.flow.Flow

interface ITouristicAttractionsInteractor {

    fun getTouristicAttractions(): Flow<Result<List<TouristicAttractionModel>>>
    suspend fun getTouristicAttractionById(id: Int): Result<TouristicAttractionModel?>
}