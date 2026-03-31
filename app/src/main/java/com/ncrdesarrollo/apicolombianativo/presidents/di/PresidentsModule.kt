package com.ncrdesarrollo.apicolombianativo.presidents.di

import com.ncrdesarrollo.apicolombianativo.presidents.data.IPresidentsDataSource
import com.ncrdesarrollo.apicolombianativo.presidents.data.PresidentsApiService
import com.ncrdesarrollo.apicolombianativo.presidents.data.PresidentsDataSource
import com.ncrdesarrollo.apicolombianativo.presidents.data.PresidentsRepository
import com.ncrdesarrollo.apicolombianativo.presidents.data.local.PresidentsDao
import com.ncrdesarrollo.apicolombianativo.presidents.domain.IPresidentsRepository
import com.ncrdesarrollo.apicolombianativo.presidents.domain.PresidentsInteractor
import com.ncrdesarrollo.apicolombianativo.regions.domain.IPresidentsInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresidentsModule {

    @Singleton
    @Provides
    fun providesApiPresidents(retrofit: Retrofit): PresidentsApiService {
        return retrofit.create(PresidentsApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesPresidentsDataSource(apiPresidents: PresidentsApiService, dao: PresidentsDao): IPresidentsDataSource {
        return PresidentsDataSource(apiPresidents, dao)
    }

    @Singleton
    @Provides
    fun providesPresidentsRepository(dataSource: IPresidentsDataSource): IPresidentsRepository {
        return PresidentsRepository(dataSource)
    }

    @Singleton
    @Provides
    fun providesPresidentsInteractor(repository: IPresidentsRepository): IPresidentsInteractor {
        return PresidentsInteractor(repository)
    }

}