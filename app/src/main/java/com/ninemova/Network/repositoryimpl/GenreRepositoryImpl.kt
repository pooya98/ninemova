package com.ninemova.Network.repositoryimpl

import com.ninemova.Network.RetrofitUtils
import com.ninemova.Network.repository.GenreRepository
import com.ninemova.domain.data.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenreRepositoryImpl : GenreRepository {

    private val api = RetrofitUtils.genreApi

    override suspend fun getGenres(genres: List<Int>): Flow<List<Genre>> = flow {
        runCatching {
            api.getGenres()
        }.onSuccess { response ->
            response.body()?.let { body ->
                val filteredGenres = body.genres.filter { genreResponse ->
                    genreResponse.id in genres
                }.map { genreResponse ->
                    Genre(
                        name = genreResponse.name,
                    )
                }
                emit(filteredGenres)
            } ?: run {
                emit(emptyList())
            }
        }.onFailure {
            emit(emptyList())
        }
    }
}
