# Step 1: Use a base image with Java
FROM openjdk:11-jdk-slim

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the JAR file into the container
COPY *.jar app.jar

# Step 4: Expose the application port
EXPOSE 8080

# Step 5: Define the entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]