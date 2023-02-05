package com.zattoo.movies.ui


/*
Events in home screen
 */
sealed class HomeScreenEvent {
    object GetMoviesList : HomeScreenEvent()
}
