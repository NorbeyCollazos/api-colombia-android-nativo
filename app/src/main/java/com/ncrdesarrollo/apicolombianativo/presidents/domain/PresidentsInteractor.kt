package com.ncrdesarrollo.apicolombianativo.presidents.domain

import com.ncrdesarrollo.apicolombianativo.presidents.ui.model.PresidentModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PresidentsInteractor @Inject constructor(private val repository: IPresidentsRepository):
    IPresidentsInteractor {
    override fun getPresidents(): Flow<Result<List<PresidentModel>>> {
        return repository.getPresidents()
    }

    override suspend fun getPresidentById(id: Int): Result<PresidentModel?> {
        return repository.getPresidentById(id)
    }
}