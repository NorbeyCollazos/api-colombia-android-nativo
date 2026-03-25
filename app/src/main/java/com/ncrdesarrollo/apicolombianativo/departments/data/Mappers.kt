package com.ncrdesarrollo.apicolombianativo.departments.data

import com.ncrdesarrollo.apicolombianativo.departments.data.local.CityCapitalEntity
import com.ncrdesarrollo.apicolombianativo.departments.data.local.DepartmentEntity
import com.ncrdesarrollo.apicolombianativo.departments.ui.model.CityCapital
import com.ncrdesarrollo.apicolombianativo.departments.ui.model.DepartmentModel

fun DepartmentEntity.toDomain(): DepartmentModel = DepartmentModel(
    id = id,
    name = name,
    description = description,
    cityCapitalId = cityCapitalId,
    municipalities = municipalities,
    surface = surface,
    population = population,
    phonePrefix = phonePrefix,
    countryId = countryId,
    cityCapital = CityCapital(
        id = cityCapitalEntity.idCity,
        name = cityCapitalEntity.nameCity,
        description = cityCapitalEntity.descriptionCity,
        surface = cityCapitalEntity.surfaceCity,
        population = cityCapitalEntity.populationCity,
        postalCode = cityCapitalEntity.postalCodeCity,
        departmentId = cityCapitalEntity.departmentIdCity
    ),
    regionId = regionId
)

fun DepartmentDto.toDomain(): DepartmentModel = DepartmentModel(
    id = id,
    name = name,
    description = description,
    cityCapitalId = cityCapitalId,
    municipalities = municipalities,
    surface = surface,
    population = population,
    phonePrefix = phonePrefix,
    countryId = countryId,
    cityCapital = CityCapital(
        id = cityCapital.id,
        name = cityCapital.name,
        description = cityCapital.description,
        surface = cityCapital.surface,
        population = cityCapital.population,
        postalCode = cityCapital.postalCode,
        departmentId = cityCapital.departmentId
    ),
    regionId = regionId
)

fun DepartmentModel.toEntity(): DepartmentEntity = DepartmentEntity(
    id = id ?: 0,
    name = name ?: "",
    description = description ?: "",
    cityCapitalId = cityCapitalId ?: 0,
    municipalities = municipalities ?: 0,
    surface = surface ?: 0,
    population = population ?: 0,
    phonePrefix = phonePrefix ?: "",
    countryId = countryId ?: 0,
    cityCapitalEntity = CityCapitalEntity(
        idCity = cityCapital?.id ?: 0,
        nameCity = cityCapital?.name ?: "",
        descriptionCity = cityCapital?.description ?: "",
        surfaceCity = cityCapital?.surface ?: 0,
        populationCity = cityCapital?.population ?: 0,
        postalCodeCity = cityCapital?.postalCode ?: "",
        departmentIdCity = cityCapital?.id ?: 0
    ),
    regionId = regionId ?: 0
)