package org.example.template.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.template.domain.User.Companion.user
import org.example.template.payload.UserPayload
import org.example.template.repository.UserRepository
import org.example.template.view.UserView
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    suspend fun getAllUsers(): Flow<UserView> = userRepository.findAll().map { it.toView() }

    suspend fun getUsersPaging(page: Int, size: Int): Flow<UserView> {
        val pageable = PageRequest.of(page, size)
        return userRepository.findAllBy(pageable).map { it.toView() }
    }

    suspend fun saveUser(payload: UserPayload): UserView {
        return userRepository.save(user {
            name = payload.name
            email = payload.email
        }).toView()
    }
}
