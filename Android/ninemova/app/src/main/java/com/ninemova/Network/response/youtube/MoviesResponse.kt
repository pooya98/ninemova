package com.ninemova.Network.response.youtube

data class MoviesResponse(
    val page: Int? = null,
    val results: List<MovieResult> = listOf(),
)
