package uk.acm64.openweather.feature.pollution.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.fail
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import uk.acm64.openweather.feature.pollution.CoroutinesTestRule
import uk.acm64.openweather.feature.pollution.domain.model.LatLon
import uk.acm64.openweather.feature.pollution.domain.model.PollutionInformation
import uk.acm64.openweather.feature.pollution.domain.repository.ApiRepository
import java.util.*


@RunWith(MockitoJUnitRunner::class)
internal class GetPollutionHistoryUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    lateinit var apiRepository: ApiRepository

    @MockK
    lateinit var exception: Exception

    @MockK
    lateinit var pollutionInformation: PollutionInformation

    @MockK
    lateinit var latLon: LatLon

    lateinit var cut: GetPollutionHistoryUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = GetPollutionHistoryUseCase(apiRepository)
    }

    @Test
    fun `run Use Case`() = runBlockingTest {

        coEvery { apiRepository.getPollutionHistory(any(), any()) } returns listOf(pollutionInformation)
        val dateRange = Date()..Date()

        val result = cut.run(GetPollutionHistoryUseCase.Params(latLon, dateRange))

        Assert.assertTrue(result.isRight)
        result.either({ fail("Should be right") },
            {
                it.size `should be equal to` 1
                it[0] `should be` pollutionInformation

            })
        coVerify { apiRepository.getPollutionHistory(latLon, dateRange) }
    }

    @Test
    fun `Use Case failure`() = runBlockingTest {

        coEvery { apiRepository.getPollutionHistory(any(), any()) } throws exception
        val dateRange = Date()..Date()

        val result = cut.run(GetPollutionHistoryUseCase.Params(latLon, dateRange))

        Assert.assertTrue(result.isLeft)
        result.either({
            it `should be` GetPollutionHistoryUseCase.GetPollutionHistoryFailure

        }, { fail("Should be left") })
        coVerify { apiRepository.getPollutionHistory(latLon, dateRange) }
    }
}
