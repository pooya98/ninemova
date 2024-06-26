package com.ninemova.ninemova.controller

import com.ninemova.ninemova.dto.FavoriteMovie
import com.ninemova.ninemova.service.FavoriteMovieService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/favoriteMovie")
@RequiredArgsConstructor
@Validated
class FavoriteMovieController(private val favoriteMovieService: FavoriteMovieService) {

    @PostMapping("/create")
    fun createLike(@RequestBody favoriteMovie: FavoriteMovie): ResponseEntity<FavoriteMovie> {
        return ResponseEntity.ok(favoriteMovieService.createFavoriteMovie(favoriteMovie))
    }

    @GetMapping("/getList/{userId}")
    fun getFavoriteMovies(@PathVariable userId: Int): ResponseEntity<List<String>> {
        val list = favoriteMovieService.getLikeMovieNamesByUserId(userId).map { it.movieName }
        return ResponseEntity.ok(list)
    }

    @DeleteMapping("/delete/{userId}/{movieId}")
    fun deleteFavorite(@PathVariable userId: Int, @PathVariable movieId: Int): ResponseEntity<Boolean> {
        return ResponseEntity.ok(favoriteMovieService.deleteMovie(userId, movieId))
    }

    @GetMapping("/find/{userId}/{movieId}")
    fun getFavorite(@PathVariable userId: Int, @PathVariable movieId: Int): ResponseEntity<Boolean> {
        val result = favoriteMovieService.findByUserIdAndMovieId(userId, movieId)
        return if (result == null) {
            ResponseEntity.ok(false)
        } else {
            ResponseEntity.ok(true)
        }
    }
}
