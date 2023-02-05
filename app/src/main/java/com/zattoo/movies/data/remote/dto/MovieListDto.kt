package com.zattoo.movies.data.remote.dto

import com.squareup.moshi.Json

data class MovieListDto(
    @field:Json(name = "movie_data")
    val movieData: List<MovieData>
) {
    data class MovieData(
        @field:Json(name = "movie_id")
        val movieId: Int,
        @field:Json(name = "sub_title")
        val subTitle: String,
        val title: String
    )
}
