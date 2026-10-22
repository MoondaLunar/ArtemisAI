# Multi-stage build for the Spring Boot backend used by docker-compose.yml
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN mvn -q package -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=build /app/target/artemis-ai-*.jar /app/artemis.jar
ENTRYPOINT ["java", "-jar", "/app/artemis.jar", "--spring.profiles.active=postgres"]
