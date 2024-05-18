package com.ninemova.ninemova.controller

import com.ninemova.ninemova.dto.User
import com.ninemova.ninemova.service.UserService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import mu.KotlinLogging

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
class UserController(private val userService: UserService) {
    private val logger = KotlinLogging.logger {}

    @PostMapping("/signUp")
    fun signUp(@RequestBody user: User) {
        userService.createUser(user)
    }
}
