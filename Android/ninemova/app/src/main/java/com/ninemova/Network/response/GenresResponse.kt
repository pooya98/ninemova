package com.ninemova.Network.response

data class GenresResponse(
    val id: Int? = null,
    val name: String? = null,
)

data class GetGenresResponse(
    val genres: List<GenresResponse> = listOf(),
)