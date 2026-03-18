package com.ncrdesarrollo.apicolombianativo.info.domain

import com.ncrdesarrollo.apicolombianativo.info.data.InfoRepository
import com.ncrdesarrollo.apicolombianativo.info.ui.model.InfoModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class InfoInteractorTest {

    @RelaxedMockK
    private lateinit var infoRepository: InfoRepository

    private lateinit var infoInteractor: InfoInteractor

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        infoInteractor = InfoInteractor(infoRepository)
    }

    @Test
    fun whenTheApiReturnsInfoThenGetInfoFromRepository() = runBlocking {

        val mockInfo = InfoModel(
            id = 1,
            name = "Colombia",
            description = "País en Sudamérica",
            surface = 1141748,
            population = 50000000,
            languages = listOf("Español"),
            timeZone = "UTC-5",
            currency = "Peso Colombiano",
            currencyCode = "COP",
            currencySymbol = "$",
            isoCode = "CO",
            internetDomain = ".co",
            phonePrefix = "+57",
            radioPrefix = "HJ",
            aircraftPrefix = "HK",
        )

        coEvery { infoRepository.getInfo() } returns mockInfo

        //When
        val response = infoInteractor.getInfo()

        // Then
        assert(response == mockInfo)
        assert(response.name == "Colombia")

    }

}