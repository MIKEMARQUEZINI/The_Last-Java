# Use the official OpenJDK 17 base image with a slim Buster (Debian 10) distribution
FROM openjdk:17-jdk-slim-buster

# Set the working directory inside the container to /app
WORKDIR /app

# Copy the JAR file from the target directory of the build context into the container at /app
COPY target/the-last-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8383 to the outside world, allowing external access to the application
EXPOSE 8080

# Define the default command to run when the container starts, launching the Java application using the JAR file
CMD ["java", "-jar", "app.jar"]
