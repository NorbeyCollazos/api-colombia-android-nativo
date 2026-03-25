package com.ncrdesarrollo.apicolombianativo.info.data

import com.ncrdesarrollo.apicolombianativo.info.data.local.InfoEntity
import com.ncrdesarrollo.apicolombianativo.info.ui.model.InfoModel

fun InfoEntity.toDomain(): InfoModel {
    return InfoModel(
        aircraftPrefix = aircraftPrefix,
        borders = borders,
        currency = currency,
        currencyCode = currencyCode,
        currencySymbol = currencySymbol,
        description = description,
        flags = flags,
        id = id,
        internetDomain = internetDomain,
        isoCode = isoCode,
        languages = languages,
        name = name,
        phonePrefix = phonePrefix,
        population = population,
        radioPrefix = radioPrefix,
        region = region,
        stateCapital = stateCapital,
        subRegion = subRegion,
        surface = surface,
        timeZone = timeZone
    )
}

fun InfoDto.toDomain(): InfoModel {
    return InfoModel(
        aircraftPrefix = aircraftPrefix,
        borders = borders,
        currency = currency,
        currencyCode = currencyCode,
        currencySymbol = currencySymbol,
        description = description,
        flags = flags,
        id = id,
        internetDomain = internetDomain,
        isoCode = isoCode,
        languages = languages,
        name = name,
        phonePrefix = phonePrefix,
        population = population,
        radioPrefix = radioPrefix,
        region = region,
        stateCapital = stateCapital,
        subRegion = subRegion,
        surface = surface,
        timeZone = timeZone
    )
}

fun InfoModel.toEntity(): InfoEntity {
    return InfoEntity(
        aircraftPrefix = aircraftPrefix,
        borders = borders,
        currency = currency,
        currencyCode = currencyCode,
        currencySymbol = currencySymbol,
        description = description,
        flags = flags,
        id = id,
        internetDomain = internetDomain,
        isoCode = isoCode,
        languages = languages,
        name = name,
        phonePrefix = phonePrefix,
        population = population,
        radioPrefix = radioPrefix,
        region = region,
        stateCapital = stateCapital,
        subRegion = subRegion,
        surface = surface,
        timeZone = timeZone
    )
}