package com.zattoo.movies.presentation.viewmodel


/*
Events in home screen
 */
sealed class HomeScreenEvent {
    object GetMoviesList : HomeScreenEvent()
}
