package com.ncrdesarrollo.apicolombianativo.regions.data

import com.ncrdesarrollo.apicolombianativo.departments.ui.model.RegionModel
import com.ncrdesarrollo.apicolombianativo.regions.data.RegionDto
import com.ncrdesarrollo.apicolombianativo.regions.data.local.RegionsEntity

fun RegionsEntity.toDomain(): RegionModel = RegionModel(
    id = id,
    name = name,
    description = description
)

fun RegionDto.toDomain(): RegionModel = RegionModel(
    id = id,
    name = name,
    description = description
)

fun RegionModel.toEntity(): RegionsEntity = RegionsEntity(
    id = id ?: 0,
    name = name ?: "",
    description = description ?: ""
)