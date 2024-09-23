package org.example.template.repository

import org.bson.types.ObjectId
import org.example.template.domain.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository : CoroutineCrudRepository<User, ObjectId>
