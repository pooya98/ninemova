package com.ninemova.Network.request

data class FavoriteRequest(
    val userId: Int,
    val movieId: Int,
    val movieName: String,
)
