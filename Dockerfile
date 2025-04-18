FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim
ARG JAR_FILE=/app/target/reservation-system-0.0.1.jar
COPY --from=build ${JAR_FILE} reservation-system.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "reservation-system.jar"]