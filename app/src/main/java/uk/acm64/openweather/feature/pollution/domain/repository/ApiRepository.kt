package uk.acm64.openweather.feature.pollution.domain.repository

import uk.acm64.openweather.feature.pollution.domain.model.LatLon
import uk.acm64.openweather.feature.pollution.domain.model.PollutionInformation
import java.util.*

interface ApiRepository {
    suspend fun getPollutionHistory(
        latLon: LatLon,
        range: ClosedRange<Date>
    ): List<PollutionInformation>
}