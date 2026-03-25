package com.ncrdesarrollo.apicolombianativo.info.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "info_table")
data class InfoEntity(
    @PrimaryKey
    val id: Int? = null,
    val aircraftPrefix: String? = null,
    val borders: List<String>? = null,
    val currency: String? = null,
    val currencyCode: String? = null,
    val currencySymbol: String? = null,
    val description: String? = null,
    val flags: List<String>? = null,
    val internetDomain: String? = null,
    val isoCode: String? = null,
    val languages: List<String>? = null,
    val name: String? = null,
    val phonePrefix: String? = null,
    val population: Int? = null,
    val radioPrefix: String? = null,
    val region: String? = null,
    val stateCapital: String? = null,
    val subRegion: String? = null,
    val surface: Int? = null,
    val timeZone: String? = null
)
