package com.ncrdesarrollo.apicolombianativo.departments.di

import com.ncrdesarrollo.apicolombianativo.departments.data.DepartmentsApiService
import com.ncrdesarrollo.apicolombianativo.departments.data.DepartmentsDataSource
import com.ncrdesarrollo.apicolombianativo.departments.data.DepartmentsRepository
import com.ncrdesarrollo.apicolombianativo.departments.data.IDepartmentsDataSource
import com.ncrdesarrollo.apicolombianativo.departments.data.local.DepartmentsDao
import com.ncrdesarrollo.apicolombianativo.departments.domain.DepartmentsInteractor
import com.ncrdesarrollo.apicolombianativo.departments.domain.IDepartmentsInteractor
import com.ncrdesarrollo.apicolombianativo.departments.domain.IDepartmentsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DepartmentsModule {

    @Singleton
    @Provides
    fun providesApiDepartments(retrofit: Retrofit): DepartmentsApiService {
        return retrofit.create(DepartmentsApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesDepartmentsDataSource(apiDepartments: DepartmentsApiService, dao: DepartmentsDao): IDepartmentsDataSource {
        return DepartmentsDataSource(apiDepartments, dao)
    }

    @Singleton
    @Provides
    fun providesDepartmentsRepository(dataSource: IDepartmentsDataSource): IDepartmentsRepository {
        return DepartmentsRepository(dataSource)
    }

    @Singleton
    @Provides
    fun providesDepartmentsInteractor(repository: IDepartmentsRepository): IDepartmentsInteractor {
        return DepartmentsInteractor(repository)
    }

}