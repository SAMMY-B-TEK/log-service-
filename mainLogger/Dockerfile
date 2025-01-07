# Use a base image with Java runtime
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar from target directory to the app directory in the container
COPY target/service-0.0.1-SNAPSHOT.jar app.jar

# Copy the OpenTelemetry Java agent to the container from the target/libs directory
COPY target/libs/opentelemetry-javaagent-1.33.5.jar /app/libs/opentelemetry-javaagent-1.33.5.jar

# Set environment variables for OpenTelemetry and Java tool options
ENV JAVA_TOOL_OPTIONS="-javaagent:/app/libs/opentelemetry-javaagent-1.33.5.jar"
ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://tempo:4317
ENV OTEL_METRICS_EXPORTER=none

# Expose the port your application runs on
EXPOSE 8022

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
