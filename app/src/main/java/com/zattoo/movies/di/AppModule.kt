package com.zattoo.movies.di

import android.content.Context
import com.zattoo.movies.data.remote.MovieApi
import com.zattoo.movies.utils.NetworkUtils
import common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
            )
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideNetworkUtils(context: Context): NetworkUtils {
        return NetworkUtils(context)
    }
}