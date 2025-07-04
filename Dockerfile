# ---- Build Stage ----
FROM maven:3.9.5-eclipse-temurin-17-alpine AS build

# Set working directory inside the nested folder
WORKDIR /app

# Copy only the inner expense-tracker folder where pom.xml exists
COPY expense-tracker /app

# Run Maven build inside that subfolder
RUN mvn clean package -DskipTests

# ---- Run Stage ----
FROM eclipse-temurin:17-jdk-alpine

# Working directory inside runtime image
WORKDIR /app

# Copy final jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render sets $PORT)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
