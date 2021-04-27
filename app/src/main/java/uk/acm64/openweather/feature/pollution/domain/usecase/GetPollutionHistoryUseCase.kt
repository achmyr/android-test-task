package uk.acm64.openweather.feature.pollution.domain.usecase

import android.util.Range
import uk.acm64.core.Either
import uk.acm64.core.Failure
import uk.acm64.core.UseCase
import uk.acm64.openweather.feature.pollution.domain.model.LatLon
import uk.acm64.openweather.feature.pollution.domain.model.PollutionInformation
import uk.acm64.openweather.feature.pollution.domain.repository.ApiRepository
import java.util.*
import javax.inject.Inject

class GetPollutionHistoryUseCase @Inject constructor(private val apiRepository: ApiRepository) :
    UseCase<List<PollutionInformation>, GetPollutionHistoryUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, List<PollutionInformation>> {
        return try {
            apiRepository.getPollutionHistory(params.latLon, params.range).let {
                Either.Right(it)
            }
        } catch (exp: Exception) {
            Either.Left(GetPollutionHistoryFailure)
        }
    }

    data class Params(val latLon: LatLon, val range: ClosedRange<Date>)
    object GetPollutionHistoryFailure : Failure.FeatureFailure()
}
