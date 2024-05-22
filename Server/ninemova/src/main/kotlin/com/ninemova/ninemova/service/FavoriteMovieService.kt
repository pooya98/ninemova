package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.FavoriteMovie

interface FavoriteMovieService {

    fun createFavoriteMovie(favoriteMovie: FavoriteMovie): FavoriteMovie
    fun deleteMovie(userId: Int, movieId: Int): Boolean
    fun getLikeMovieNamesByUserId(userId: Int): List<FavoriteMovie>
    fun findByUserIdAndMovieId(userId: Int, movieId: Int): FavoriteMovie?
}
