package com.ninemova.ninemova.repository

import com.ninemova.ninemova.dto.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Int> {

    fun findUserByUserName(username: String): User?
}
