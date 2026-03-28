package com.ncrdesarrollo.apicolombianativo.regions.data

import com.google.gson.annotations.SerializedName

data class RegionDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,
)