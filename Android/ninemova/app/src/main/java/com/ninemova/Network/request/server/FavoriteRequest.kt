package com.ninemova.Network.request.server

data class FavoriteRequest(
    val userId: Int,
    val movieId: Int,
    val movieName: String,
)
