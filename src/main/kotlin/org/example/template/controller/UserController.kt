package org.example.template.controller

import kotlinx.coroutines.flow.Flow
import org.example.template.domain.User
import org.example.template.payload.UserPayload
import org.example.template.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    suspend fun getAllUsers(): Flow<User> = userService.findAllUsers()

    @PostMapping
    suspend fun saveUser(@RequestBody payload: UserPayload): User = userService.saveUser(payload)
}
