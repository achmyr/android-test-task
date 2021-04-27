package uk.acm64.openweather.feature.pollution.presentation.history

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.`should be`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.acm64.openweather.feature.pollution.CoroutinesTestRule
import uk.acm64.openweather.feature.pollution.domain.usecase.GetPollutionHistoryUseCase

internal class PollutionHistoryViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    lateinit var getPollutionHistoryUseCase: GetPollutionHistoryUseCase


    lateinit var cut: PollutionHistoryViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = PollutionHistoryViewModel(getPollutionHistoryUseCase)
    }

    @Test
    fun `perform catalog fetch`() {
        every {getPollutionHistoryUseCase.invoke(any(), any(), any())} just Runs
        cut.loadData()

        cut.state.value `should be` PollutionHistoryViewState.Loading
        verify { getPollutionHistoryUseCase.invoke(any(), any(), any())}
    }

}