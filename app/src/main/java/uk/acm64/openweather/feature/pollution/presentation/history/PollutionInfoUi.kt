package uk.acm64.openweather.feature.pollution.presentation.history

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PollutionInfoUi(val date: String, val co: String) : Parcelable