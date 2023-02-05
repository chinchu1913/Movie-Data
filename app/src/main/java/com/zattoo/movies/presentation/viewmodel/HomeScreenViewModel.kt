package com.zattoo.movies.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.zattoo.movies.common.Resource
import com.zattoo.movies.common.utils.NetworkUtils
import com.zattoo.movies.domain.entities.Movie
import com.zattoo.movies.domain.repository.MovieRepository
import com.zattoo.movies.ui.HomeScreenEvent
import com.zattoo.movies.ui.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
ViewModel for folder Home screen
 */
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val networkUtils: NetworkUtils
) :
    ViewModel() {
    var state by mutableStateOf(HomeScreenState())
    private var moviesList: List<Movie> = mutableListOf()

    init {
        viewModelScope.launch {
            getMoviesList()
            observeNetworkConnection()
        }
    }

    private suspend fun observeNetworkConnection() {
        networkUtils.getNetworkLiveData().asFlow().collect {
            state = state.copy(isNetworkConnected = it)
        }
    }

    /*
    Method to get the movie list from the repository notify the states
     */
    private fun getMoviesList() {
        viewModelScope.launch {
            repository
                .getMovies()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                moviesList = listings
                                state = state.copy(
                                    moviesList = moviesList,
                                )
                            }
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                errorMessage = "Error Loading Items"
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    /*
    Method to handle the  events from the UI
     */
    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.GetMoviesList -> {
                getMoviesList()
            }
        }
    }

}