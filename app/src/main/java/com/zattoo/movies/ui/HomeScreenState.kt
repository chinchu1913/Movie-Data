package com.zattoo.movies.ui

import com.zattoo.movies.domain.entities.Movie

/*
States in home screen
 */
data class HomeScreenState(
    val moviesList: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val isNetworkConnected: Boolean = true,
    val errorMessage: String = "",
)
