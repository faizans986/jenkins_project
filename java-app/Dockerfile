# Use an official Maven image to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (layer caching)
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# -------------------------------
# Runtime Image
# -------------------------------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the jar file built in the first stage
COPY --from=build /app/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]
