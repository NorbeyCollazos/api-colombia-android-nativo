package com.ncrdesarrollo.apicolombianativo.info.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface InfoDao {

    @Upsert
    suspend fun upsertInfo(infoEntity: InfoEntity)

    @Query("SELECT * FROM info_table")
    fun getInfo(): Flow<InfoEntity>

}