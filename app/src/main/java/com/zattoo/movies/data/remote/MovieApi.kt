package com.zattoo.movies.data.remote

import com.zattoo.movies.data.remote.dto.MovieListDto
import com.zattoo.movies.data.remote.dto.MovieListOffersDto
import retrofit2.http.GET

interface MovieApi {
    @GET("movie_offers.json")
    suspend fun fetchMovieListOffers(): MovieListOffersDto

    @GET("movie_data.json")
    suspend fun fetchMovieList(): MovieListDto
}