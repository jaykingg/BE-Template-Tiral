package org.example.template.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import org.example.template.domain.User
import org.example.template.payload.UserPayload
import org.example.template.repository.UserRepository
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest : BehaviorSpec({
    val userRepository = mockk<UserRepository>()
    val userService = UserService(userRepository)

    given("UserService를 사용할 때") {

        `when`("새로운 사용자를 저장하면") {
            val payload = UserPayload(name = "John Doe", email = "john@example.com")
            val user = User(name = payload.name, email = payload.email)

            // MockK를 사용하여 save 동작 모킹
            coEvery { userRepository.save(any()) } returns user

            then("사용자가 저장되어야 한다") {
                val savedUser = userService.saveUser(payload)
                savedUser shouldBe user

                // 리포지토리의 save 함수가 호출되었는지 검증
                coVerify(exactly = 1) { userRepository.save(any()) }
            }
        }

        `when`("모든 사용자를 조회하면") {
            val user1 = User(name = "John Doe", email = "john@example.com")
            val user2 = User(name = "Jane Doe", email = "jane@example.com")
            val userList = listOf(user1, user2)

            // MockK를 사용하여 findAll 동작 모킹
            coEvery { userRepository.findAll() } returns userList.asFlow()

            then("모든 사용자가 반환되어야 한다") {
                val users = userService.findAllUsers().toList()
                users shouldBe userList

                // 리포지토리의 findAll 함수가 호출되었는지 검증
                coVerify(exactly = 1) { userRepository.findAll() }
            }
        }
    }
})
