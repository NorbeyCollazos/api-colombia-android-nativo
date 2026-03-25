package com.ncrdesarrollo.apicolombianativo.departments.data

import com.google.gson.annotations.SerializedName

data class DepartmentDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("cityCapitalId")
    val cityCapitalId: Int,

    @SerializedName("municipalities")
    val municipalities: Int,

    @SerializedName("surface")
    val surface: Int,

    @SerializedName("population")
    val population: Int,

    @SerializedName("phonePrefix")
    val phonePrefix: String,

    @SerializedName("countryId")
    val countryId: Int,

    @SerializedName("cityCapital")
    val cityCapital: CityCapital,

    @SerializedName("regionId")
    val regionId: Int
)

data class CityCapital(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("surface")
    val surface: Int,

    @SerializedName("population")
    val population: Int,

    @SerializedName("postalCode")
    val postalCode: String,

    @SerializedName("departmentId")
    val departmentId: Int,
)