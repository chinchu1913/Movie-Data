package com.zattoo.movies.common.utils

fun priceAndAvailabilityToString(price: String, isAvailable: Boolean): String {
    return when (isAvailable) {
        true -> String.format("%s %s", price, "Available")
        false -> String.format("%s %s", price, "Sold out")
    }
}