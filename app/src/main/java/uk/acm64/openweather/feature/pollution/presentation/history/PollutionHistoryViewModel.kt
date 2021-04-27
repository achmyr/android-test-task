package uk.acm64.openweather.feature.pollution.presentation.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import uk.acm64.core.BaseViewModel
import uk.acm64.openweather.feature.pollution.domain.model.LatLon
import uk.acm64.openweather.feature.pollution.domain.model.PollutionInformation
import uk.acm64.openweather.feature.pollution.domain.usecase.GetPollutionHistoryUseCase
import java.util.*
import javax.inject.Inject

class PollutionHistoryViewModel @Inject constructor(val getPollutionHistoryUseCase: GetPollutionHistoryUseCase) :
    BaseViewModel() {

    var state: MutableLiveData<PollutionHistoryViewState> = MutableLiveData()

    fun loadData(latLon: LatLon = LatLon(50f, 50f), startDate: Date = Date(1619551053000), endDate: Date = Date()) {
        state.value = PollutionHistoryViewState.Loading
        getPollutionHistoryUseCase(viewModelScope, GetPollutionHistoryUseCase.Params(latLon, startDate..endDate)) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(list: List<PollutionInformation>) {
        when {
            list.isEmpty() -> state.value = PollutionHistoryViewState.Empty
            else -> state.value = PollutionHistoryViewState.Available(mapToPresentation(list))
        }
    }

    private fun mapToPresentation(list: List<PollutionInformation>): List<PollutionInfoUi> = list.map {
        PollutionInfoUi(it.date.toString(), "CO: ${it.co}")
    }
}

sealed class PollutionHistoryViewState() {
    object Loading : PollutionHistoryViewState()
    object Empty : PollutionHistoryViewState()
    data class Available(val data: List<PollutionInfoUi>) : PollutionHistoryViewState()
}
