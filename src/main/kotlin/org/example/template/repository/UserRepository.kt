package org.example.template.repository

import org.bson.types.ObjectId
import org.example.template.domain.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserRepository : CoroutineCrudRepository<User, ObjectId>
