package org.example.template.config

import org.example.template.service.UserDummyService
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfiguration {

    // 애플리케이션 시작 시 더미 데이터를 생성하는 ApplicationRunner 빈 정의
    @Bean
    fun runOnStartup(userDummyService: UserDummyService) = ApplicationRunner {
        userDummyService.createDummyUsers()
    }
}
