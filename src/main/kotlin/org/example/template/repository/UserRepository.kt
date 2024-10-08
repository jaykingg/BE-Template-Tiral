package org.example.template.repository

import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.example.template.domain.User
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

@EnableReactiveMongoRepositories
interface UserRepository : CoroutineCrudRepository<User, ObjectId> {
    suspend fun findAllBy(pageable: Pageable): Flow<User>
}
