FROM openjdk:8-jdk-alpine
EXPOSE 8081
ARG JAR_FILE=target/imagestorage-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
RUN mkdir upload-dir
RUN chmod -R a+rw upload-dir
ENTRYPOINT ["java","-jar","app.jar"]