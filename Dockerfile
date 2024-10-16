# Use a valid Maven image with a supported JDK version
FROM maven:3.9.7-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Use Amazon Corretto JDK for running the application
FROM amazoncorretto:22
WORKDIR /app
COPY --from=build /build/target/docker-spring-boot-*.jar /app/

EXPOSE 8088

CMD ["java", "-jar", "docker-spring-boot.jar"]
