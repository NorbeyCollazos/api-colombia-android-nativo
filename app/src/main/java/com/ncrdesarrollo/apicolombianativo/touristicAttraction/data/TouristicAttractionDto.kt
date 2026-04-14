package com.ncrdesarrollo.apicolombianativo.touristicAttraction.data

import com.google.gson.annotations.SerializedName

data class TouristicAttractionDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("images")
    val images: List<String>,

    @SerializedName("latitude")
    val latitude: String,

    @SerializedName("longitude")
    val longitude: String,

    @SerializedName("cityId")
    val cityId: Int
)