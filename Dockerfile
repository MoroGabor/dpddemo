# Build Stage
FROM openjdk:17-jdk-slim AS builder


WORKDIR /app

COPY . /app

RUN ./mvnw clean package -DskipTests

# Package Stage
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

CMD ["java", "-jar", "demo.jar"]
