package com.zattoo.movies.domain.repository

import com.zattoo.movies.domain.entities.Movie
import common.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<Resource<List<Movie>>>
}