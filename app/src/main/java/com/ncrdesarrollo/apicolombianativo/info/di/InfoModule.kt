package com.ncrdesarrollo.apicolombianativo.info.di

import com.ncrdesarrollo.apicolombianativo.info.data.IInfoDataSource
import com.ncrdesarrollo.apicolombianativo.info.data.InfoApiService
import com.ncrdesarrollo.apicolombianativo.info.data.InfoDataSource
import com.ncrdesarrollo.apicolombianativo.info.data.InfoRepository
import com.ncrdesarrollo.apicolombianativo.info.data.local.InfoDao
import com.ncrdesarrollo.apicolombianativo.info.domain.IInfoInteractor
import com.ncrdesarrollo.apicolombianativo.info.domain.IInfoRepository
import com.ncrdesarrollo.apicolombianativo.info.domain.InfoInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InfoModule {

    @Provides
    @Singleton
    fun providesApiInfo(retrofit: Retrofit): InfoApiService {
        return retrofit.create(InfoApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesDatasource(apiInfo: InfoApiService, dao: InfoDao): IInfoDataSource =
        InfoDataSource(apiInfo, dao)


    @Provides
    @Singleton
    fun providesRepository(dataSource: IInfoDataSource): IInfoRepository =
        InfoRepository(dataSource)

    @Provides
    @Singleton
    fun providesInteractor(repository: IInfoRepository): IInfoInteractor =
        InfoInteractor(repository)

}