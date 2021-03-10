FROM openjdk:8-jdk-alpine
EXPOSE 8081
FROM maven:3.6.0-jdk-8-slim AS build
RUN mvn clean package
RUN ls -ltr
ARG JAR_FILE=/target/imagestorage-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

