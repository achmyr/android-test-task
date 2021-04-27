package uk.acm64.openweather.contract.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uk.acm64.core.di.viewmodel.ViewModelFactory
import uk.acm64.core.di.viewmodel.ViewModelKey
import uk.acm64.openweather.feature.pollution.presentation.history.PollutionHistoryViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PollutionHistoryViewModel::class)
    abstract fun bindsPollutionHistoryViewModel(pollutionHistoryViewModel: PollutionHistoryViewModel): ViewModel

}
