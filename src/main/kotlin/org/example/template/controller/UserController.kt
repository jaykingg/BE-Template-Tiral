package org.example.template.controller

import kotlinx.coroutines.flow.Flow
import org.example.template.payload.UserPayload
import org.example.template.service.UserService
import org.example.template.view.UserView
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping("/all")
    suspend fun getAllUsers(): Flow<UserView> = userService.getAllUsers()

    @GetMapping
    suspend fun getUsers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Flow<UserView> = userService.getUsersPaging(page, size)

    @PostMapping
    suspend fun saveUser(@RequestBody payload: UserPayload): UserView = userService.saveUser(payload)
}
