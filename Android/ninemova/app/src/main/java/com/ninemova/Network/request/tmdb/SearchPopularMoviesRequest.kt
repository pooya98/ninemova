package com.ninemova.Network.request.tmdb

data class SearchPopularMoviesRequest(
    val language: String? = "ko",
    val page: Int? = 2,
    val region: String? = "",
)