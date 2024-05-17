package com.ninemova.Network

import com.ninemova.Network.repository.MovieRepository
import com.ninemova.Network.repositoryimpl.MovieRepositoryImpl

class RepositoryUtils {

    companion object {
        val movieRepository: MovieRepository = MovieRepositoryImpl()
    }
}
