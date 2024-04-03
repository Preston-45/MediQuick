# Use a lightweight base image
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/Tibu-0.0.1-SNAPSHOT.jar /app/

# Expose the port your application listens on
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "Tibu-0.0.1-SNAPSHOT.jar"]
