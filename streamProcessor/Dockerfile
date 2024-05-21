# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the build.sbt and project files to the container
COPY build.sbt /app/
COPY project /app/project

# Fetch the dependencies in a separate layer to leverage Docker's caching
RUN apt-get update && apt-get install -y curl && \
    curl -L -o sbt.deb http://dl.bintray.com/sbt/debian/sbt-1.5.5.deb && \
    dpkg -i sbt.deb && \
    sbt update

# Copy the rest of the application code to the container
COPY . /app

# Compile the application
RUN sbt compile

# Package the application
RUN sbt assembly

# Set the entrypoint command to run the jar file
ENTRYPOINT ["java", "-jar", "target/scala-2.13/your-app-assembly-0.1.jar"]

# Expose the application's port (if applicable)
EXPOSE 8080
