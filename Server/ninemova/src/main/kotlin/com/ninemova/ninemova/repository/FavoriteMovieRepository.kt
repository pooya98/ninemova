package com.ninemova.ninemova.repository

import com.ninemova.ninemova.dto.FavoriteMovie
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FavoriteMovieRepository : CrudRepository<FavoriteMovie, Int> {
    fun findAllByUserId(userId: Int): List<FavoriteMovie>
}