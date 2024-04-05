# Build Stage
FROM amazoncorretto:17-alpine-jdk AS build

WORKDIR /app

COPY .mvn .mvn
COPY mvnw .

COPY pom.xml .
COPY src src
RUN chmod +x mvnw
RUN dos2unix mvnw
RUN ./mvnw clean package

# Package Stage
FROM amazoncorretto:17-alpine-jdk

RUN adduser -g user -s /usr/bin/nologin --home /home/user user --disabled-password
WORKDIR /home/user

COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar ./demo.jar

EXPOSE 8080

CMD ["java", "-jar", "demo.jar"]
