package com.ncrdesarrollo.apicolombianativo.presidents.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "presidents")
data class PresidentEntity(
    @PrimaryKey val id: Int,
    val image: String,
    val name: String,
    val lastName: String,
    val startPeriodDate: String,
    val endPeriodDate: String,
    val politicalParty: String,
    val description: String,
)


