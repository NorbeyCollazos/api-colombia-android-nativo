package com.ncrdesarrollo.apicolombianativo.info.domain

import com.ncrdesarrollo.apicolombianativo.info.ui.model.InfoModel
import kotlinx.coroutines.flow.Flow

interface IInfoRepository {

    fun getInfo(): Flow<Result<InfoModel>>
}