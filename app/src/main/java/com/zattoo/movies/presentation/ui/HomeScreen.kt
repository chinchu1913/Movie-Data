package com.zattoo.movies.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.zattoo.movies.R
import com.zattoo.movies.common.utils.priceAndAvailabilityToString
import com.zattoo.movies.presentation.viewmodel.HomeScreenEvent
import com.zattoo.movies.presentation.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val isRefreshing = SwipeRefreshState(isRefreshing = state.isLoading)

    Scaffold(topBar = { AppBarComponent(stringResource(id = R.string.text_movies)) }) {

        SwipeRefresh(
            state = isRefreshing,
            onRefresh = {
                viewModel.onEvent(HomeScreenEvent.GetMoviesList)
            },
        ) {
            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp),
                modifier = Modifier.fillMaxSize()
            ) {

                items(state.moviesList.size) { i ->
                    val movie = state.moviesList[i]
                    SingleListItem(
                        title = movie.title,
                        subtitle1 = movie.subtitle,
                        subtitle2 = priceAndAvailabilityToString(
                            "${movie.price.value}${movie.price.currency.component1()}",
                            movie.availability
                        ),
                        imageUrl = movie.image.url
                    )
                }
            }
            if (state.showConnectionError) {
                NotConnectedComponent()
            }

            if (state.showConnected) {
                ConnectedComponent()
            }

            val showErrorLoadingData = state.moviesList.isEmpty() && state.isError
            if (showErrorLoadingData) {
                ErrorComponent()
            }
        }
    }
}
