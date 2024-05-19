package com.ninemova.ninemova.controller

import com.ninemova.ninemova.dto.User
import com.ninemova.ninemova.service.UserService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated

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
}
