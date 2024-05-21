package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.User
import com.ninemova.ninemova.repository.UserRepository
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
@AllArgsConstructor
class UserServiceImpl(val userRepository: UserRepository) : UserService {

    override fun createUser(user: User): User {
        return userRepository.save(user)
    }

    override fun findUser(id: Int): User? = userRepository.findById(id).getOrNull()
}
