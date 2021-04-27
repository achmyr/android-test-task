package uk.acm64.openweather.feature.pollution.data.repository

import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import uk.acm64.openweather.feature.pollution.data.ApiRetrofitService
import uk.acm64.openweather.feature.pollution.data.model.PollutionHistoryResponse
import uk.acm64.openweather.feature.pollution.domain.model.LatLon
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ApiRepositoryImplTest {

    @MockK
    lateinit var apiRetrofitService: ApiRetrofitService

    @MockK
    lateinit var pollutionHistoryResponse: PollutionHistoryResponse

    @MockK
    lateinit var latLon: LatLon

    @MockK
    lateinit var apiKeyProvider: ApiKeyProvider

    lateinit var apiRepositoryImpl: ApiRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        apiRepositoryImpl = ApiRepositoryImpl(apiRetrofitService, apiKeyProvider)
    }

    @Test
    fun `get data`() = runBlockingTest {
        coEvery { apiRetrofitService.getPollutionHistory(any(), any(), any(), any(), any()) } returns pollutionHistoryResponse
        val date = Date()
        every { latLon.lat } returns 11f
        every { latLon.lon } returns 12f
        every { apiKeyProvider.apiKey } returns "API_KEY"
        every { pollutionHistoryResponse.toData()} returns listOf()

        val data = apiRepositoryImpl.getPollutionHistory(latLon, date..date)

        coVerify {apiRetrofitService.getPollutionHistory(11f, 12f, date.time, date.time, "API_KEY" )}
        data.size `should be equal to` 0
    }

}