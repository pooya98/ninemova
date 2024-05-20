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

    override fun deleteFavoriteMovie(id: Int): Boolean {
        if (favoriteMovieRepository.existsById(id).not()) {
            return false
        }
        favoriteMovieRepository.deleteById(id)
        return true
    }

    override fun getLikeMovieNamesByUserId(userId: Int): List<FavoriteMovie> {
        return favoriteMovieRepository.findAllByUserId(userId)
    }
}
