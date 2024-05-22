FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the application JAR file into the container
COPY app/NiceTeam-0.0.1-SNAPSHOT.jar /app/NiceTeam-0.0.1-SNAPSHOT.jar

# Expose the port the application runs on
EXPOSE 8080

CMD ["java", "-jar", "/app/NiceTeam-0.0.1-SNAPSHOT.jar"]