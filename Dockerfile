FROM ubuntu:latest as build
RUN apt-get update
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=build libs/how-much-pay-api-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]