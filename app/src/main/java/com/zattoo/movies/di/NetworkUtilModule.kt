package com.zattoo.movies.di

import com.zattoo.movies.common.utils.NetworkUtil
import com.zattoo.movies.common.utils.NetworkUtilsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkUtilModule {

    @Binds
    @Singleton
    abstract fun buildNetworkUtil(
        networkUtilImpl: NetworkUtilsImpl,
    ): NetworkUtil

}

