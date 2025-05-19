# Etapa 1: Build con Maven desde el proyecto raíz
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /build

# Copiamos todo el proyecto (incluye pom padre y submódulos)
COPY . .

# Compilamos solo el módulo turnos-api
RUN mvn -pl turnos-api clean package -DskipTests

# Etapa 2: Imagen liviana para ejecución
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiamos el JAR generado del submódulo
COPY --from=builder /build/turnos-api/target/turnos-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9002

ENTRYPOINT ["java", "-jar", "app.jar"]
