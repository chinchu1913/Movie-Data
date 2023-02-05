package com.zattoo.movies.domain.repository

import com.zattoo.movies.domain.entities.Movie
import com.zattoo.movies.common.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<Resource<List<Movie>>>
}