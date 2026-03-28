package com.ncrdesarrollo.apicolombianativo.regions.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RegionsDao {

    @Upsert
    suspend fun upsertRegion(regions: List<RegionsEntity>)

    @Query("SELECT * FROM regions")
    fun getRegions(): Flow<List<RegionsEntity>>

    @Query("SELECT * FROM regions WHERE id = :regionId LIMIT 1")
    suspend fun getRegionById(regionId: Int): RegionsEntity?
}