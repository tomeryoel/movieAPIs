# Use an official Maven image to build the project
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and the source code to the container
COPY pom.xml ./
COPY src ./src

# Run Maven to build the project and package it as a JAR file
RUN mvn clean package -DskipTests

# Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container for the final image
WORKDIR /app

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/target/*.jar app.jar

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]