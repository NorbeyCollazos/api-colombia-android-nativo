package com.ncrdesarrollo.apicolombianativo.info.domain

import com.ncrdesarrollo.apicolombianativo.info.ui.model.InfoModel

interface IInfoInteractor {

    suspend fun getInfo(): InfoModel
}