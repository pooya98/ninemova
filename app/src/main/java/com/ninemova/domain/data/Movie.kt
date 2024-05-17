package com.ninemova.domain.data

import java.io.Serializable

data class Movie(
    val id: Int? = null,
    val title: String? = null,
    val genreIds: List<Int>? = null,
    val overView: String? = null,
    val adult: Boolean? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null
) : Serializable
