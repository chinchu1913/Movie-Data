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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
ViewModel for Movie Home screen
 */
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val networkUtils: NetworkUtils
) :
    ViewModel() {
    var state by mutableStateOf(HomeScreenState())
    private var moviesList: List<Movie> = mutableListOf()
    private var lastConnectionStatus = true

    init {
        viewModelScope.launch {
            getMoviesList()
            observeNetworkConnection()
        }
    }

    private suspend fun observeNetworkConnection() {
        networkUtils.getNetworkLiveData().asFlow().collect { isConnected ->
            //show the connection error is the connection status is disconnected
            state = state.copy(showNetworkUnavailable = !isConnected)
            //show the connection success message if the connection if disconnected and reconnected back.
            val isConnectionIsBack = !lastConnectionStatus && isConnected
            if (isConnectionIsBack) {
                coroutineScope {
                    //Refresh the data once the connection is back
                    getMoviesList()
                    state = state.copy(showNetworkConnected = true)
                    delay(ViewModelConstants.CONNECTION_BACK_MSG_TIMEOUT)
                    state = state.copy(showNetworkConnected = false)
                }
            }
            lastConnectionStatus = isConnected
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
                                    isError = false
                                )
                            }
                        }
                        is Resource.Error -> {
                            if (moviesList.isEmpty()) {
                                state = state.copy(
                                    isError = true,
                                )
                            }
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

    object ViewModelConstants {
        const val CONNECTION_BACK_MSG_TIMEOUT = 2000L
    }
}