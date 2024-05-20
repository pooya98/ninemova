package com.ninemova.ninemova.service

import com.ninemova.ninemova.dto.User
import com.ninemova.ninemova.repository.UserRepository
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
@AllArgsConstructor
class UserServiceImpl(val userRepository: UserRepository) : UserService {

    override fun createUser(user: User) : User {
        return userRepository.save(user)
    }

    override fun findByUserName(userName: String): User? {
        return userRepository.findUserByUserName(userName)
    }
}
