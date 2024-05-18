package com.ninemova.Network.request

data class SearchMovieRequest(
    val includeAdult: Boolean? = false,
    val language: String? = "ko",
    val page: Int? = 1,
    val query: String? = "",
)
