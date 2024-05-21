package com.ninemova.Network.repository

import com.ninemova.Network.request.SearchMovieRequest
import com.ninemova.Network.request.SearchNowPlayingMoviesRequest
import com.ninemova.Network.request.SearchPopularMoviesRequest
import com.ninemova.domain.data.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun searchMovies(request: SearchMovieRequest): Flow<List<Movie>>

    suspend fun searchNowPlayingMovies(request: SearchNowPlayingMoviesRequest): Flow<List<Movie>>

    suspend fun searchPopularMovies(request: SearchPopularMoviesRequest): Flow<List<Movie>>
}
