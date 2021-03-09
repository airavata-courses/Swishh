FROM openjdk:8-jdk-alpine
EXPOSE 8081
WORKDIR home/circleci/repo/ImageStorage
ARG JAR_FILE=/target/imagestorage-0.0.1-SNAPSHOT.jar
RUN pwd
RUN ls -ltr
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]