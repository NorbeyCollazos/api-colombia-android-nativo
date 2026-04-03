package com.ncrdesarrollo.apicolombianativo.core.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "info_colombia_database"

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, InfoColombiaDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideInfoDao(db: InfoColombiaDatabase) = db.infoDao()

    @Singleton
    @Provides
    fun provideDepartments(db: InfoColombiaDatabase) = db.departmentsDao()

    @Singleton
    @Provides
    fun provideRegions(db: InfoColombiaDatabase) = db.regionsDao()

    @Singleton
    @Provides
    fun providePresidents(db: InfoColombiaDatabase) = db.presidentsDao()

    @Singleton
    @Provides
    fun provideTouristicAttractions(db: InfoColombiaDatabase) = db.touristicAttractionsDao()

}