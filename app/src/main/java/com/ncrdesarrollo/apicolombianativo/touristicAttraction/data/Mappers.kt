package com.ncrdesarrollo.apicolombianativo.touristicAttraction.data

import com.ncrdesarrollo.apicolombianativo.touristicAttraction.data.local.TouristicAttractionEntity
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.ui.model.TouristicAttractionModel

fun TouristicAttractionEntity.toDomain(): TouristicAttractionModel = TouristicAttractionModel(
    id = id,
    name = name,
    description = description,
    images = images,
    latitude = latitude,
    longitude = longitude,
    cityId = cityId
)

fun TouristicAttractionDto.toDomain(): TouristicAttractionModel = TouristicAttractionModel(
    id = id,
    name = name,
    description = description,
    images = images,
    latitude = latitude,
    longitude = longitude,
    cityId = cityId
)

fun TouristicAttractionModel.toEntity(): TouristicAttractionEntity = TouristicAttractionEntity(
    id = id ?: 0,
    name = name ?: "",
    description = description ?: "",
    images = images ?: emptyList(),
    latitude = latitude ?: "",
    longitude = longitude ?: "",
    cityId = cityId ?: 0
)