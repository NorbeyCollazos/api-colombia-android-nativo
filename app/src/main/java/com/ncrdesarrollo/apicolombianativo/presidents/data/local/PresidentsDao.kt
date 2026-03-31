package com.ncrdesarrollo.apicolombianativo.presidents.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PresidentsDao {

    @Upsert
    suspend fun upsertPresident(presidents: List<PresidentEntity>)

    @Query("SELECT * FROM presidents")
    fun getPresidents(): Flow<List<PresidentEntity>>

    @Query("SELECT * FROM presidents WHERE id = :presidentId LIMIT 1")
    suspend fun getPresidentById(presidentId: Int): PresidentEntity?
}