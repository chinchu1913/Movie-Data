package com.zattoo.movies.data.remote.dto

import com.squareup.moshi.Json

data class MovieListOffersDto(
    @field:Json(name = "image_base")
    val imageBase: String,
    @field:Json(name = "movie_offers")
    val movieOffers: List<MovieOffer>
) {
    data class MovieOffer(
        val available: Boolean,
        val image: String,
        @field:Json(name = "movie_id")
        val movieId: Int,
        val price: String
    )
}
