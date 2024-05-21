package com.ninemova.ui.recommend

import com.ninemova.domain.data.Genre
import com.ninemova.domain.data.Movie

data class RecommendUiState(
    val query: String? = "",
    val aiRecommendMovie: Movie? = null,
    val tmdbRecommendMovie: Movie? = null,
    val newWorldRecommendMovie: Movie? = null,
    val selectedMovie: Movie? = null,
    val genres: List<Genre> = listOf()
)
