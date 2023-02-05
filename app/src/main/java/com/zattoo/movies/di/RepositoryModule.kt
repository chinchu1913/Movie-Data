package com.zattoo.movies.di

import com.zattoo.movies.data.repository.MovieRepositoryImpl
import com.zattoo.movies.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun buildMovieRepository(
        storageRepositoryImpl: MovieRepositoryImpl,
    ): MovieRepository
}
