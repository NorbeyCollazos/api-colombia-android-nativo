package com.ncrdesarrollo.apicolombianativo.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ncrdesarrollo.apicolombianativo.core.utils.Converters
import com.ncrdesarrollo.apicolombianativo.info.data.local.InfoDao
import com.ncrdesarrollo.apicolombianativo.info.data.local.InfoEntity

@Database(entities = [InfoEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class InfoColombiaDatabase: RoomDatabase() {

    abstract fun infoDao(): InfoDao
}