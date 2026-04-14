package com.ncrdesarrollo.apicolombianativo.touristicAttraction.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TouristicAttractionDao {

    @Upsert
    suspend fun upsertTouristicAttraction(touristicAttraction: List<TouristicAttractionEntity>)

    @Query("SELECT * FROM touristic_attraction")
    fun getTouristicAttractions(): Flow<List<TouristicAttractionEntity>>

    @Query("SELECT * FROM touristic_attraction WHERE id = :touristicAttractionId LIMIT 1")
    suspend fun getTouristicAttractionById(touristicAttractionId: Int): TouristicAttractionEntity?
}