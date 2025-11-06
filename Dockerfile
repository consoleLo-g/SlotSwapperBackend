# ============================
# Stage 1: Build
# ============================
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy Maven config files for dependency caching
COPY pom.xml . 
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the Spring Boot app (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# ============================
# Stage 2: Run
# ============================
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy built jar from build stage
COPY --from=build /app/target/SlotSwapperBackend-*.jar app.jar

# Expose the port Render will use
EXPOSE 8080

# ============================
# Environment Variables for Render
# ============================
# These will be set in Render dashboard
ENV JWT_SECRET=""
ENV JWT_EXPIRATION=86400000
ENV MONGODB_URI=""
ENV SPRING_PROFILES_ACTIVE=prod

# ============================
# Start the application
# ============================
ENTRYPOINT ["java", "-jar", "app.jar"]
