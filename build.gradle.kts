plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.spring") version "1.8.0"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

    // Kotest for testing
    testImplementation("io.kotest:kotest-runner-junit5:5.0.3")
    testImplementation("io.kotest:kotest-assertions-core:5.0.3")

    // Redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // MongoDB Reactive
    implementation("org.springframework.data:spring-data-mongodb")

    // Jackson for JSON support
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Reactor test (for WebFlux)
    testImplementation("io.projectreactor:reactor-test")
}
