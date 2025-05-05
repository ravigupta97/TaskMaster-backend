# Use a base image with JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Set a working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# Package the app (skip tests)
RUN ./mvnw clean package -DskipTests

# Run the JAR
CMD ["java", "-jar", "target/taskmanager-0.0.1-SNAPSHOT.jar"]
