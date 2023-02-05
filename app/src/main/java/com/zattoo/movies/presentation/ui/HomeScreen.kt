package com.zattoo.movies.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.zattoo.movies.common.utils.priceAndAvailabilityToString
import com.zattoo.movies.presentation.viewmodel.HomeScreenViewModel
import com.zattoo.movies.ui.HomeScreenEvent
import com.zattoo.movies.ui.theme.ToolbarColor
import com.zattoo.movies.ui.theme.ToolbarTextColor

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val isRefreshing = SwipeRefreshState(isRefreshing = state.isLoading)

    Scaffold(topBar = { AppBarComponent("Movies") }) {

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
        }


        if (state.isNetworkConnected) {
            Text(text = "Network is connected")
        }

    }
}

/*
Custom composable for the app bar
 */
@Composable
fun AppBarComponent(
    title: String,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = ToolbarColor,
        elevation = 2.dp
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            title, textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = ToolbarTextColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}