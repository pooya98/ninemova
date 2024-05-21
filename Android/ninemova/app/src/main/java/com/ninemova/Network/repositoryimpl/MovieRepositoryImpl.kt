package com.ninemova.Network.repositoryimpl

import com.ninemova.Network.RetrofitUtils
import com.ninemova.Network.repository.MovieRepository
import com.ninemova.Network.request.SearchMovieRequest
import com.ninemova.Network.request.SearchNowPlayingMoviesRequest
import com.ninemova.Network.request.SearchPopularMoviesRequest
import com.ninemova.domain.data.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl : MovieRepository {

    private val api = RetrofitUtils.searchApi

    override suspend fun searchMovies(request: SearchMovieRequest): Flow<List<Movie>> =
        flow {
            runCatching {
                api.getMovies(
                    includeAdult = request.includeAdult ?: false,
                    language = request.language ?: "ko",
                    page = request.page ?: 1,
                    query = request.query ?: "",
                )
            }.onSuccess {
                it.body()?.let { response ->
                    emit(
                        response.results.filter { it.posterPath != null }.map { movieResponse ->
                            Movie(
                                id = movieResponse.id,
                                title = movieResponse.title,
                                genreIds = movieResponse.genreIds ?: listOf(),
                                adult = movieResponse.adult,
                                overView = movieResponse.overView,
                                posterPath = movieResponse.posterPath ?: movieResponse.backdropPath,
                                backdropPath = movieResponse.backdropPath
                                    ?: movieResponse.posterPath,
                                releaseDate = movieResponse.releaseDate,
                            )
                        },
                    )
                }
            }.onFailure {
                emit(emptyList())
            }
        }

    override suspend fun searchNowPlayingMovies(request: SearchNowPlayingMoviesRequest): Flow<List<Movie>> =
        flow {
            runCatching {
                api.getNowPlayingMovies(
                    language = request.language ?: "ko",
                    page = request.page ?: 1,
                )
            }.onSuccess {
                it.body()?.let { response ->
                    emit(
                        response.results.filter { it.posterPath != null }.map { movieResponse ->
                            Movie(
                                id = movieResponse.id,
                                title = movieResponse.title,
                                genreIds = movieResponse.genreIds ?: listOf(),
                                adult = movieResponse.adult,
                                overView = movieResponse.overView,
                                posterPath = movieResponse.posterPath ?: movieResponse.backdropPath,
                                backdropPath = movieResponse.backdropPath
                                    ?: movieResponse.posterPath,
                                releaseDate = movieResponse.releaseDate,
                            )
                        }.subList(0, 5),
                    )
                }
            }.onFailure {
                emit(emptyList())
            }
        }

    override suspend fun searchPopularMovies(request: SearchPopularMoviesRequest): Flow<List<Movie>> =
        flow {
            runCatching {
                api.getPopularMovies(
                    language = request.language ?: "ko",
                    page = request.page ?: 2,
                )
            }.onSuccess {
                it.body()?.let { response ->
                    emit(
                        response.results.filter { it.posterPath != null }.map { movieResponse ->
                            Movie(
                                id = movieResponse.id,
                                title = movieResponse.title,
                                genreIds = movieResponse.genreIds ?: listOf(),
                                adult = movieResponse.adult,
                                overView = movieResponse.overView,
                                posterPath = movieResponse.posterPath ?: movieResponse.backdropPath,
                                backdropPath = movieResponse.backdropPath
                                    ?: movieResponse.posterPath,
                                releaseDate = movieResponse.releaseDate,
                            )
                        },
                    )
                }
            }.onFailure {
                emit(emptyList())
            }
        }
}
