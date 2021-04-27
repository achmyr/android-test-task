package uk.acm64.openweather.feature.pollution.data

import retrofit2.http.GET
import retrofit2.http.Query
import uk.acm64.openweather.feature.pollution.data.model.PollutionHistoryResponse

interface ApiRetrofitService {
    @GET("air_pollution/history")
    suspend fun getPollutionHistory(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("start") start: Long,
        @Query("end") end: Long,
        @Query("appid") appId: String
    ): PollutionHistoryResponse

}
