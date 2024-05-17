package com.ninemova.Network

import com.ninemova.Network.repository.GenreRepository
import com.ninemova.Network.repository.MovieRepository
import com.ninemova.Network.repositoryimpl.GenreRepositoryImpl
import com.ninemova.Network.repositoryimpl.MovieRepositoryImpl

class RepositoryUtils {

    companion object {
        val movieRepository: MovieRepository = MovieRepositoryImpl()
        val genreRepository: GenreRepository = GenreRepositoryImpl()
    }
}
