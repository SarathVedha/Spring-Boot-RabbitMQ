FROM eclipse-temurin:17
LABEL authors="sarath"

WORKDIR /app
COPY target/SpringBootRabbitMQ-0.0.1-SNAPSHOT.jar /app/SpringBootRabbitMQ.jar

ENTRYPOINT ["java", "-jar", "SpringBootRabbitMQ.jar"]