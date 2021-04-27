package uk.acm64.openweather.feature.pollution.data.repository

import android.util.Range
import uk.acm64.openweather.feature.pollution.data.ApiRetrofitService
import uk.acm64.openweather.feature.pollution.domain.model.LatLon
import uk.acm64.openweather.feature.pollution.domain.model.PollutionInformation
import uk.acm64.openweather.feature.pollution.domain.repository.ApiRepository
import java.util.*
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiRetrofitService: ApiRetrofitService, private val apiKeyProvider: ApiKeyProvider) :
    ApiRepository {
    override suspend fun getPollutionHistory(
        latLon: LatLon,
        range: ClosedRange<Date>
    ): List<PollutionInformation> =
        apiRetrofitService.getPollutionHistory(
            latLon.lat,
            latLon.lon,
            range.start.time,
            range.endInclusive.time,
            apiKeyProvider.apiKey
        )
            .toData()
}