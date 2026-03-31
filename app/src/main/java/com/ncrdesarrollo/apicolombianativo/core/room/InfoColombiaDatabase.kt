package com.ncrdesarrollo.apicolombianativo.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ncrdesarrollo.apicolombianativo.core.utils.Converters
import com.ncrdesarrollo.apicolombianativo.departments.data.local.DepartmentEntity
import com.ncrdesarrollo.apicolombianativo.departments.data.local.DepartmentsDao
import com.ncrdesarrollo.apicolombianativo.info.data.local.InfoDao
import com.ncrdesarrollo.apicolombianativo.info.data.local.InfoEntity
import com.ncrdesarrollo.apicolombianativo.presidents.data.local.PresidentEntity
import com.ncrdesarrollo.apicolombianativo.presidents.data.local.PresidentsDao
import com.ncrdesarrollo.apicolombianativo.regions.data.local.RegionsDao
import com.ncrdesarrollo.apicolombianativo.regions.data.local.RegionsEntity

@Database(entities = [InfoEntity::class, DepartmentEntity::class, RegionsEntity::class, PresidentEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class InfoColombiaDatabase: RoomDatabase() {

    abstract fun infoDao(): InfoDao
    abstract fun departmentsDao(): DepartmentsDao
    abstract fun regionsDao(): RegionsDao
    abstract fun presidentsDao(): PresidentsDao
}