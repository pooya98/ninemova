package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.FavoriteMovie
import com.ninemova.ninemova.repository.FavoriteMovieRepository
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
@AllArgsConstructor
class FavoriteMovieServiceImpl(private val favoriteMovieRepository: FavoriteMovieRepository) : FavoriteMovieService {

    override fun createFavoriteMovie(favoriteMovie: FavoriteMovie): FavoriteMovie {
        return favoriteMovieRepository.save(favoriteMovie)
    }

    override fun deleteMovie(userId: Int, movieId: Int): Boolean {
        val favoriteMovie = favoriteMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: return false
        favoriteMovieRepository.delete(favoriteMovie)
        return true
    }


    override fun getLikeMovieNamesByUserId(userId: Int): List<FavoriteMovie> {
        return favoriteMovieRepository.findAllByUserId(userId)
    }

    override fun findByUserIdAndMovieId(userId: Int, movieId: Int): FavoriteMovie? {
        return favoriteMovieRepository.findByUserIdAndMovieId(userId, movieId)
    }
}
