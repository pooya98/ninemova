package com.ninemova.ninemova.controller

import com.ninemova.ninemova.dto.User
import com.ninemova.ninemova.service.UserService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Validated
class UserController(private val userService: UserService) {

    @PostMapping("/signUp")
    fun signUp(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.ok(userService.createUser(user))
    }

    @GetMapping("/{id}")
    fun findUser(@PathVariable id: Int): ResponseEntity<User?> {
        val user = userService.findUser(id) ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        return ResponseEntity.ok(user)
    }
}
