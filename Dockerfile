# Stage 1: Build the JAR
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /app/target/QuizApp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["sh", "-c", "sleep 10 && java -jar app.jar"]
