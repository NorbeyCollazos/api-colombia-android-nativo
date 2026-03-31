package com.ncrdesarrollo.apicolombianativo.presidents.data

import com.ncrdesarrollo.apicolombianativo.presidents.data.local.PresidentEntity
import com.ncrdesarrollo.apicolombianativo.presidents.ui.model.PresidentModel

fun PresidentEntity.toDomain(): PresidentModel = PresidentModel(
    id = id,
    image = image,
    name = name,
    lastName = lastName,
    startPeriodDate = startPeriodDate,
    endPeriodDate = endPeriodDate,
    politicalParty = politicalParty,
    description = description
)

fun PresidentDto.toDomain(): PresidentModel = PresidentModel(
    id = id,
    image = image,
    name = name,
    lastName = lastName,
    startPeriodDate = startPeriodDate,
    endPeriodDate = endPeriodDate,
    politicalParty = politicalParty,
    description = description
)

fun PresidentModel.toEntity(): PresidentEntity = PresidentEntity(
    id = id ?: 0,
    image = image ?: "",
    name = name ?: "",
    lastName = lastName ?: "",
    startPeriodDate = startPeriodDate ?: "",
    endPeriodDate = endPeriodDate ?: "",
    politicalParty = politicalParty ?: "",
    description = description ?: ""
)