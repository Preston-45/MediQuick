# Use a lightweight base image
FROM ubuntu:20.04

# Set the working directory in the container
WORKDIR /app

# Set noninteractive frontend
ENV DEBIAN_FRONTEND=noninteractive

# Install necessary packages (including wget for downloading JDK)
RUN apt-get update \
    && apt-get install -y wget \
    && rm -rf /var/lib/apt/lists/*

# Download and install JDK 22
RUN wget https://download.java.net/java/GA/jdk22/830ec9fcccef480bb3e73fb7ecafe059/36/GPL/openjdk-22_linux-x64_bin.tar.gz \
    && tar -xzf openjdk-22_linux-x64_bin.tar.gz -C /usr/local \
    && rm openjdk-22_linux-x64_bin.tar.gz \
    && update-alternatives --install /usr/bin/java java /usr/local/jdk-22/bin/java 100

# Copy the packaged JAR file into the container
COPY target/Tibu-0.0.1-SNAPSHOT.jar /app/

# Expose the port your application listens on
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "Tibu-0.0.1-SNAPSHOT.jar"]
