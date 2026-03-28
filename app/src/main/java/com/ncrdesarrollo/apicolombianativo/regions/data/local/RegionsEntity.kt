package com.ncrdesarrollo.apicolombianativo.regions.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "regions")
data class RegionsEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
)


