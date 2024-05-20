package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.FavoriteMovie

interface FavoriteMovieService {

    fun createFavoriteMovie(favoriteMovie: FavoriteMovie): FavoriteMovie
    fun deleteFavoriteMovie(id: Int): Boolean
    fun getLikeMovieNamesByUserId(userId: Int): List<FavoriteMovie>
}
