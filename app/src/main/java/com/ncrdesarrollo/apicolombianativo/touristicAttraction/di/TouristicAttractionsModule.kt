package com.ncrdesarrollo.apicolombianativo.touristicAttraction.di

import com.ncrdesarrollo.apicolombianativo.touristicAttraction.data.ITouristicAttractionDataSource
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.data.TouristicAttractionsApiService
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.data.TouristicAttractionsDataSource
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.data.TouristicAttractionsRepository
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.data.local.TouristicAttractionDao
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.domain.ITouristicAttractionsInteractor
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.domain.ITouristicAttractionsRepository
import com.ncrdesarrollo.apicolombianativo.touristicAttraction.domain.TouristicAttractionsInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TouristicAttractionsModule {

    @Singleton
    @Provides
    fun providesApiTouristicAttractions(retrofit: Retrofit): TouristicAttractionsApiService {
        return retrofit.create(TouristicAttractionsApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesTouristicAttractionsDataSource(apiTouristicAttractions: TouristicAttractionsApiService, dao: TouristicAttractionDao): ITouristicAttractionDataSource {
        return TouristicAttractionsDataSource(apiTouristicAttractions, dao)
    }

    @Singleton
    @Provides
    fun providesTouristicAttractionsRepository(dataSource: ITouristicAttractionDataSource): ITouristicAttractionsRepository {
        return TouristicAttractionsRepository(dataSource)
    }

    @Singleton
    @Provides
    fun providesTouristicAttractionsInteractor(repository: ITouristicAttractionsRepository): ITouristicAttractionsInteractor {
        return TouristicAttractionsInteractor(repository)
    }

}