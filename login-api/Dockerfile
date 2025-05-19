# login-api/Dockerfile
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /build
COPY . .

RUN mvn -pl login-api clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /build/login-api/target/login-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9001

ENTRYPOINT ["java", "-jar", "app.jar"]
