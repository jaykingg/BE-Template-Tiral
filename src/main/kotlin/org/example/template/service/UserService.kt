package org.example.template.service

import kotlinx.coroutines.flow.Flow
import org.example.template.domain.User
import org.example.template.domain.User.Companion.user
import org.example.template.payload.UserPayload
import org.example.template.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    suspend fun findAllUsers(): Flow<User> = userRepository.findAll()

    suspend fun saveUser(payload: UserPayload): User {
        return userRepository.save(user {
            name = payload.name
            email = payload.email
        })
    }
}
