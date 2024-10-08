package org.example.template.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.flow.Flow
import org.example.template.payload.UserPayload
import org.example.template.service.UserService
import org.example.template.view.UserView
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@Tag(name = "User API", description = "User 관련 API")
class UserController(private val userService: UserService) {

    @Operation(summary = "사용자 목록 조회", description = "사용자 전체 조회")
    @GetMapping("/all")
    suspend fun getAllUsers(): Flow<UserView> = userService.getAllUsers()

    @Operation(summary = "사용자 목록 페이징 조회", description = "사용자 목록을 페이징 처리하여 조회")
    @GetMapping
    suspend fun getUsers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Flow<UserView> = userService.getUsersPaging(page, size)

    @Operation(summary = "사용자 저장", description = "사용자의 name, email 을 저장")
    @PostMapping
    suspend fun saveUser(@RequestBody payload: UserPayload): UserView = userService.saveUser(payload)
}
