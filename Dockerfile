# Use a lightweight base image
FROM adoptopenjdk/openjdk22:jdk-22.0.1_9-alpine-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/Tibu.jar /app/

# Expose the port your application listens on
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "Tibu.jar"]
