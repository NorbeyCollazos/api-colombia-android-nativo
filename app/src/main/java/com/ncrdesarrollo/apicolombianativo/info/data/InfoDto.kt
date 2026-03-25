package com.ncrdesarrollo.apicolombianativo.info.data

import com.google.gson.annotations.SerializedName

data class InfoDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("capital")
    val capital: String,

    @SerializedName("surface")
    val surface: Int,

    @SerializedName("population")
    val population: Int,

    @SerializedName("languages")
    val languages: List<String>,

    @SerializedName("flags")
    val flags: List<String>,

    @SerializedName("timeZone")
    val timeZone: String,

    @SerializedName("currency")
    val currency: String,

    @SerializedName("currencyCode")
    val currencyCode: String,

    @SerializedName("currencySymbol")
    val currencySymbol: String,

    @SerializedName("isoCode")
    val isoCode: String,

    @SerializedName("internetDomain")
    val internetDomain: String,

    @SerializedName("phonePrefix")
    val phonePrefix: String,

    @SerializedName("radioPrefix")
    val radioPrefix: String,

    @SerializedName("aircraftPrefix")
    val aircraftPrefix: String,

    @SerializedName("stateAbbreviation")
    val stateAbbreviation: String,

    @SerializedName("region")
    val region: String,

    @SerializedName("stateCapital")
    val stateCapital: String,

    @SerializedName("subRegion")
    val subRegion: String,

    @SerializedName("regionMap")
    val regionMap: String,

    @SerializedName("borders")
    val borders: List<String>
)
