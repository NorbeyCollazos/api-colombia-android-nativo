package com.ncrdesarrollo.apicolombianativo.presidents.data

import com.google.gson.annotations.SerializedName

data class PresidentDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("image")
    val image: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("startPeriodDate")
    val startPeriodDate: String,

    @SerializedName("endPeriodDate")
    val endPeriodDate: String,

    @SerializedName("politicalParty")
    val politicalParty: String,

    @SerializedName("description")
    val description: String,
)