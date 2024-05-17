package com.ninemova.Network.response

data class MoviesResponse(
    val page: Int? = null,
    val results: List<MovieResult> = listOf(),
)
