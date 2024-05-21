package com.ninemova.ui.home

import com.ninemova.domain.data.Comment
import com.ninemova.domain.data.Movie

data class HomeUiState(
    val query: String? = "",
    val popularMovies: List<Movie> = listOf(),
    val nowPlayingMovies: List<Movie> = listOf(),
    val recentComments: List<Comment> = listOf()
)
