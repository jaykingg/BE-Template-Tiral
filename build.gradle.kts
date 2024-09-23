plugins {
    kotlin("jvm") version "1.9.10" // 2024년 기준 최신 Kotlin 버전
    kotlin("plugin.spring") version "1.9.10"
    id("org.springframework.boot") version "3.2.0" // 2024년 9월 기준 최신 Spring Boot 버전
    id("io.spring.dependency-management") version "1.1.3"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17) // Java 17 설정
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot 및 WebFlux
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // MongoDB Reactive와 Redis Reactive 설정
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")

    // Spring Validation (사용자 입력 검증)
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Kotlinx Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3") // 최신 Coroutines Reactor
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Jackson for Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Kotest for testing
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0") // Kotest 최신 버전
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")

    // Reactor Test (WebFlux 테스트)
    testImplementation("io.projectreactor:reactor-test")

    // Spring Boot Test 의존성
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        // MockK를 사용하므로 필요에 따라 모키토를 제외
        //  exclude(group = "org.mockito")
    }

    // MockK for mocking
    testImplementation("io.mockk:mockk:1.13.7") // 최신 MockK 버전

    // 추가적인 코루틴 테스트 도구
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict") // JSR305 엄격 모드 설정
    }
}

tasks.withType<Test> {
    useJUnitPlatform() // JUnit5로 테스트 실행
}
