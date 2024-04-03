# Use a multi-stage build with Maven and OpenJDK
FROM maven:3.8.3-jdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a lightweight Alpine image for running the application
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/Tibu-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
CMD ["java", "-jar", "Tibu-0.0.1-SNAPSHOT.jar"]
