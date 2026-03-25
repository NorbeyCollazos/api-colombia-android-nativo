package com.ncrdesarrollo.apicolombianativo.info.domain

import com.ncrdesarrollo.apicolombianativo.info.ui.model.InfoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InfoInteractor @Inject constructor(private val infoRepository: IInfoRepository) : IInfoInteractor {
    override fun getInfo(): Flow<Result<InfoModel>> {
        return infoRepository.getInfo()
    }
}