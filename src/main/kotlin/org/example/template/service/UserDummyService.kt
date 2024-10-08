package org.example.template.service

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.example.template.domain.User
import org.example.template.repository.UserRepository
import org.springframework.stereotype.Service


@Service
class UserDummyService(
    private val userRepository: UserRepository
) {
    fun createDummyUsers() = runBlocking {
        userRepository.deleteAll()
        val dummyUsers = (1..100).map {
            User(
                id = ObjectId.get(),
                name = "User$it",
                email = "user$it@example.com"
            )
        }
        userRepository.saveAll(dummyUsers).collect()
    }
}
