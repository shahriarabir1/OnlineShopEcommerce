FROM maven:3.9.7-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src

# Run mvn package with debug logs to identify the issue
RUN mvn clean package -DskipTests -X

# Use Amazon Corretto JDK for running the application
FROM amazoncorretto:22
WORKDIR /app
COPY --from=build /build/target/docker-spring-boot-*.jar /app/

EXPOSE 8088

CMD ["java", "-jar", "docker-spring-boot.jar"]
