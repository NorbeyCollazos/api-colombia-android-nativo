package com.ncrdesarrollo.apicolombianativo.info.domain

import com.ncrdesarrollo.apicolombianativo.info.ui.model.InfoModel
import javax.inject.Inject

class InfoInteractor @Inject constructor(private val infoRepository: IInfoRepository) : IInfoInteractor {
    override suspend fun getInfo(): InfoModel {
        return infoRepository.getInfo()
    }
}