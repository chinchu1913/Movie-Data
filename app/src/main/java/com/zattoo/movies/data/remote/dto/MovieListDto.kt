package com.zattoo.movies.data.remote.dto

import com.squareup.moshi.Json

data class MovieListDto(
    @Json(name = "movie_data")
    val movie_data: List<MovieData>
) {
    data class MovieData(
        @Json(name = "movie_id")
        val movie_id: Int,
        @Json(name = "sub_title")
        val sub_title: String,
        val title: String
    )
}
