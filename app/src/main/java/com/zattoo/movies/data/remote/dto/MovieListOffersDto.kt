package com.zattoo.movies.data.remote.dto

import com.squareup.moshi.Json

data class MovieListOffersDto(
    val image_base: String,
    @Json(name = "movie_offers")
    val offers: List<MovieOffer>
) {
    data class MovieOffer(
        val available: Boolean,
        val image: String,
        val movie_id: Int,
        val price: String
    )
}
