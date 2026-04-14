package com.ncrdesarrollo.apicolombianativo.touristicAttraction.domain

import com.ncrdesarrollo.apicolombianativo.touristicAttraction.ui.model.TouristicAttractionModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TouristicAttractionsInteractor @Inject constructor(private val repository: ITouristicAttractionsRepository):
    ITouristicAttractionsInteractor {
    override fun getTouristicAttractions(): Flow<Result<List<TouristicAttractionModel>>> {
        return repository.getTouristicAttractions()
    }

    override suspend fun getTouristicAttractionById(id: Int): Result<TouristicAttractionModel?> {
        return repository.getTouristicAttractionById(id)
    }
}