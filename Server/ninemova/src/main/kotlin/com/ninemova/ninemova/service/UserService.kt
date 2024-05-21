package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.User

interface UserService {

    fun createUser(user: User): User

    fun findUser(id: Int): User?
}
