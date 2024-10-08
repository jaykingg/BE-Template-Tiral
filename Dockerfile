FROM gradle:8.10.1-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle build --no-daemon

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
