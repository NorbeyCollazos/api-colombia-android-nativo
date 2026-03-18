package com.ncrdesarrollo.apicolombianativo.departments.domain

import com.ncrdesarrollo.apicolombianativo.departments.data.DepartmentsRepository
import com.ncrdesarrollo.apicolombianativo.departments.ui.model.CityCapital
import com.ncrdesarrollo.apicolombianativo.departments.ui.model.DepartmentModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DepartmentsInteractorTest {

    @RelaxedMockK
    private lateinit var repository: DepartmentsRepository

    private lateinit var interactor: DepartmentsInteractor


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        interactor = DepartmentsInteractor(repository)
    }

    @Test
    fun returnListDepartmentsNotEmpty() = runBlocking {

        //GIVEN
        val listDepartment = listOf(
            DepartmentModel(
                id = 1,
                name = "Antioquia",
                description = "Descripción de antioquia"
            )
        )
        coEvery { repository.getDepartments() } returns listDepartment

        //WHEN
        val response = interactor.getDepartments()

        //THEN
        assert(response.isNotEmpty())
        assert(response == listDepartment)
    }

    @Test
    fun returnDataFromDepartmentById() = runBlocking {

        val model = DepartmentModel(
            id = 1,
            name = "Antioquia",
            description = "Descripción de antioquia",
            surface = 123456,
            population = 123456,
            cityCapital = CityCapital(
                id = 1,
                name = "Medellin",
                description = "Descripción de medellin",
                surface = 123456
            )
        )

        coEvery { repository.getDepartmentById(1) } returns model

        val response = interactor.getDepartmentById(1)

        assert(response == model)

    }

}