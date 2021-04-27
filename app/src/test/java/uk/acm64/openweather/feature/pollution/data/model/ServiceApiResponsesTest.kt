package uk.acm64.openweather.feature.pollution.data.model

import org.amshove.kluent.`should be equal to`
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ServiceApiResponsesTest {

    @Test
    fun toData() {
        val pollutionData = PollutionHistoryResponse(listOf(), listOf())

        val data = pollutionData.toData()

        data.size `should be equal to`  0
    }

}