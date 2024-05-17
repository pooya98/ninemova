package com.ninemova.Network.repository

import com.ninemova.Network.request.SearchMovieRequest
import com.ninemova.domain.data.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun searchMovies(request: SearchMovieRequest): Flow<List<Movie>>
}
