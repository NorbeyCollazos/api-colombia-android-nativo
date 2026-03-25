package com.ncrdesarrollo.apicolombianativo.departments.ui.model

data class DepartmentModel(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val cityCapitalId: Int? = null,
    val municipalities: Int? = null,
    val surface: Int? = null,
    val population: Int? = null,
    val phonePrefix: String? = null,
    val countryId: Int? = null,
    val cityCapital: CityCapital? = null,
    val regionId: Int? = null
)