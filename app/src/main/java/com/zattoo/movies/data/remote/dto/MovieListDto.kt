package com.zattoo.movies.data.remote.dto

data class MovieListDto(
    val movie_data: List<MovieData>
) {
    data class MovieData(
        val movie_id: Int,
        val sub_title: String,
        val title: String
    )
}
