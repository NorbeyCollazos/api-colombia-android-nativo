package com.ncrdesarrollo.apicolombianativo.touristicAttraction.ui.model

data class TouristicAttractionModel(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val images: List<String>? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val cityId: Int? = null
)