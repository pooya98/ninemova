package com.ninemova.ui.recommend

import com.ninemova.domain.data.Genre
import com.ninemova.domain.data.Movie

data class RecommendUiState(
    val query: String? = "",
    val aiRecommendMovieTitle: String? = "",
    val newWorldMovieTitle: String? = "",
    val selectedMovie: Movie? = null,
    val genres: List<Genre> = listOf(),
)
