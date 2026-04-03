package com.ncrdesarrollo.apicolombianativo.presidents.domain

import com.ncrdesarrollo.apicolombianativo.presidents.ui.model.PresidentModel
import kotlinx.coroutines.flow.Flow

interface IPresidentsInteractor {

    fun getPresidents(): Flow<Result<List<PresidentModel>>>
    suspend fun getPresidentById(id: Int): Result<PresidentModel?>
}