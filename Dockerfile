# Use the official OpenJDK 8 base image
FROM openjdk:8-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/personalizeddata-0.0.1-SNAPSHOT.jar /app/personalizeddata.jar

# Expose the port that your application runs on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "personalizeddata.jar"]
