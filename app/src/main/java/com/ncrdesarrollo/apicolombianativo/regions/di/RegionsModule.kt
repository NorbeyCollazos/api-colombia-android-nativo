package com.ncrdesarrollo.apicolombianativo.regions.di

import com.ncrdesarrollo.apicolombianativo.regions.data.RegionsApiService
import com.ncrdesarrollo.apicolombianativo.regions.data.RegionsDataSource
import com.ncrdesarrollo.apicolombianativo.regions.data.RegionsRepository
import com.ncrdesarrollo.apicolombianativo.regions.data.IRegionsDataSource
import com.ncrdesarrollo.apicolombianativo.regions.data.local.RegionsDao
import com.ncrdesarrollo.apicolombianativo.regions.domain.RegionsInteractor
import com.ncrdesarrollo.apicolombianativo.regions.domain.IRegionsInteractor
import com.ncrdesarrollo.apicolombianativo.regions.domain.IRegionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegionsModule {

    @Singleton
    @Provides
    fun providesApiRegions(retrofit: Retrofit): RegionsApiService {
        return retrofit.create(RegionsApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesRegionsDataSource(apiRegions: RegionsApiService, dao: RegionsDao): IRegionsDataSource {
        return RegionsDataSource(apiRegions, dao)
    }

    @Singleton
    @Provides
    fun providesRegionsRepository(dataSource: IRegionsDataSource): IRegionsRepository {
        return RegionsRepository(dataSource)
    }

    @Singleton
    @Provides
    fun providesRegionsInteractor(repository: IRegionsRepository): IRegionsInteractor {
        return RegionsInteractor(repository)
    }

}