package org.example.template.controller

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp
import io.kotest.common.runBlocking
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.collect
import org.bson.types.ObjectId
import org.example.template.domain.User
import org.example.template.repository.UserRepository
import org.example.template.view.UserView
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient
class UserControllerIT(
    private val userRepository: UserRepository,
    private val webTestClient: WebTestClient
) : BehaviorSpec({
    val endPoint = "/users"
    val fixtureMonkey: FixtureMonkey = FixtureMonkey.builder()
        .plugin(KotlinPlugin())
        .build()



    beforeEach {
        userRepository.deleteAll()
    }

    Given("사용자 목록 가져오기") {
        When("사용자 목록이 있을 경우") {
            val userBuilder = fixtureMonkey.giveMeBuilder<User>()
                .setExp(User::id) { ObjectId.get() }

            val userList = userBuilder.sampleList(10)
            println("userList::$userList")
            runBlocking {
                userRepository.saveAll(userList).collect()
            }
            Then("사용자 목록을 가져온다") {
                webTestClient
                    .get()
                    .uri(endPoint)
                    .exchange()
                    .expectStatus().isOk
                    .expectBodyList(UserView::class.java)
                    .returnResult()
                    .responseBody!!.size shouldBe 10
            }
        }
    }

//    given("새로운 사용자를 등록할 때") {
//
//        `when`("POST /users 요청을 보내면") {
//            then("사용자를 생성하고 반환한다") {
//                // Fixture Monkey로 새로운 사용자 생성
//                val newUser = fixtureMonkey.giveMeOne(User::class.java)
//
//                // WebTestClient를 사용해 POST 요청
//                webTestClient.post().uri("/users")
//                    .bodyValue(newUser)
//                    .exchange()
//                    .expectStatus().isOk
//                    .expectBody(User::class.java)
//                    .consumeWith { response ->
//                        val savedUser = response.responseBody!!
//                        savedUser.name shouldBe newUser.name
//                        savedUser.email shouldBe newUser.email
//                    }
//            }
//        }
//    }
})
