package com.neeraj.data.di

import com.google.gson.GsonBuilder
import com.neeraj.common.BuildConfig
import com.neeraj.data.network.CocktailApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Neeraj Manchanda
 * Network Module for providing network-related dependencies, such as OkHttpClient and Retrofit,
 * used for making API requests within the application.
 *
 * @property TIMEOUT The default timeout value for network operations.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TIMEOUT = 60L

    /**
     * Provides an instance of [HttpLoggingInterceptor] for logging HTTP requests and responses.
     *
     * @return An instance of [HttpLoggingInterceptor] configured based on build type.
     */
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY.takeIf { BuildConfig.DEBUG }
                ?: HttpLoggingInterceptor.Level.NONE
        }
    }

    /**
     * Provides a singleton instance of [OkHttpClient] with specified timeouts and logging interceptor.
     *
     * @param interceptor The [HttpLoggingInterceptor] for logging HTTP requests and responses.
     * @return A singleton instance of [OkHttpClient] configured with timeouts and logging.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    /**
     * Provides a singleton instance of [CocktailApiService] using Retrofit for API communication.
     *
     * @param okHttpClient The [OkHttpClient] instance for handling network requests.
     * @return A singleton instance of [CocktailApiService] for making API calls.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): CocktailApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .build()
            .create(CocktailApiService::class.java)
    }
}
