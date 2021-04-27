package uk.acm64.openweather.feature.pollution.domain.model

import java.time.LocalDateTime

data class PollutionInformation(val date: LocalDateTime, val co: Float)