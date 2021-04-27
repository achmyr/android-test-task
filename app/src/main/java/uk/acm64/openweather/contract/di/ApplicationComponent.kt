package uk.acm64.openweather.contract.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uk.acm64.openweather.AndroidApplication
import uk.acm64.openweather.feature.pollution.presentation.history.PollutionHistoryFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(androidApplication: AndroidApplication)
    fun inject(pollutionHistoryFragment: PollutionHistoryFragment)

}
