package com.ninemova.ninemova.dto

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class FavoriteMovie(
    @Id @GeneratedValue var id: Int? = null,
    @Column(nullable = false)
    val userId: Int,
    @Column(nullable = false)
    val movieId: Int,
    @Column(nullable = false)
    val movieName: String,
)
