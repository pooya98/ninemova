package com.ninemova.Network.request.tmdb

data class SearchNowPlayingMoviesRequest(
    val language: String? = "ko",
    val page: Int? = 1,
    val region: String? = "",
)
