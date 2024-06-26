package com.ninemova.Network.response.tmdb

data class GenreResponse(
    val id: Int? = null,
    val name: String? = null,
)

data class GetGenresResponse(
    val genres: List<GenreResponse> = listOf(),
)
