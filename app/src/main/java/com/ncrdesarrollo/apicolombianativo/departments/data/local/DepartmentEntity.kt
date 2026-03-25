package com.ncrdesarrollo.apicolombianativo.departments.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "departments")
data class DepartmentEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val cityCapitalId: Int,
    val municipalities: Int,
    val surface: Int,
    val population: Int,
    val phonePrefix: String,
    val countryId: Int,
    @Embedded val cityCapitalEntity: CityCapitalEntity,
    val regionId: Int
)

data class CityCapitalEntity(
    val idCity: Int,
    val nameCity: String,
    val descriptionCity: String,
    val surfaceCity: Int,
    val populationCity: Int,
    val postalCodeCity: String,
    val departmentIdCity: Int,
)

