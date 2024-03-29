package com.zattoo.movies.presentation.viewmodel

import com.zattoo.movies.domain.entities.Movie

/*
States in home screen
 */
data class HomeScreenState(
    val moviesList: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val showNetworkUnavailable: Boolean = false,
    val showNetworkConnected: Boolean = false,
    val isError: Boolean = false,
)
