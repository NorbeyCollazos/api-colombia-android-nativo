package com.ncrdesarrollo.apicolombianativo.touristicAttraction.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "touristic_attraction")
data class TouristicAttractionEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val images: List<String>,
    val latitude: String,
    val longitude: String,
    val cityId: Int
)


