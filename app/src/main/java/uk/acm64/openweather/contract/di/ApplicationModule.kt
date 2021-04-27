package uk.acm64.openweather.contract.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uk.acm64.BuildConfig
import uk.acm64.openweather.feature.pollution.data.ApiRetrofitService
import uk.acm64.openweather.feature.pollution.data.repository.ApiKeyProvider
import uk.acm64.openweather.feature.pollution.data.repository.ApiRepositoryImpl
import uk.acm64.openweather.feature.pollution.domain.repository.ApiRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule() {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(createClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesOpenWeatheRetrofitService(retrofit: Retrofit): ApiRetrofitService =
        retrofit.create(ApiRetrofitService::class.java)

    @Provides
    @Singleton
    fun providesApiKey(): ApiKeyProvider {
        return ApiKeyProvider(BuildConfig.API_KEY)
    }

    @Provides
    @Singleton
    fun providesApiRepository(apiRetrofitService: ApiRetrofitService,
    apiKeyProvider: ApiKeyProvider): ApiRepository {
        return ApiRepositoryImpl(
            apiRetrofitService,
            apiKeyProvider
        )
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun createClient(): OkHttpClient = OkHttpClient.Builder().apply {
        retryOnConnectionFailure(true)
        connectTimeout(15, TimeUnit.SECONDS)
        readTimeout(15, TimeUnit.SECONDS)
        writeTimeout(15, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            addInterceptor(loggingInterceptor)
        }
    }.build()


}
