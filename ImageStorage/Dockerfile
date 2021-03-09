FROM openjdk:8-jdk-alpine
EXPOSE 8081
ARG JAR_FILE=./home/circleci/repo/ImageStorage/target/imagestorage-0.0.1-SNAPSHOT.jar
RUN pwd
RUN ls -ltr
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]