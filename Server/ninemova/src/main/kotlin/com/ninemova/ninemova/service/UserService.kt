package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.User

interface UserService {

    fun createUser(user: User)

    fun findByUserName(userName: String): User?
}
