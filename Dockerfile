# Use a lightweight Java 17 base image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the Spring Boot JAR into the container
COPY target/Bug-Tracking-api-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the Spring Boot JAR into the container
COPY target/Bug-Tracking-api-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java","-jar","app.jar"]

