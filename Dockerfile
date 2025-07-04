# Use Maven image with JDK 17 and Alpine Linux
FROM maven:3.9.5-eclipse-temurin-17-alpine AS build

# Set working directory
WORKDIR /app

# Copy all files to the container
COPY . /app

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests

# Use lightweight JDK image to run the app
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR from the builder image
COPY --from=build /app/target/*.jar app.jar

# Expose dynamic port (Render uses $PORT env)
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "app.jar"]

