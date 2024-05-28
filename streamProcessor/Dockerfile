FROM openjdk:11-jre-slim

WORKDIR /app

# Copy the project jar to the container
COPY target/scala-2.13/fakerStream-assembly-1.0.jar /app/fakerStream-assembly-1.0.jar

ENV SPARK_MASTER_URL=spark://spark-master:7077

ENTRYPOINT ["java", "-cp", "/app/fakerStream-assembly-1.0.jar", "com.example.StreamProcessor"]
