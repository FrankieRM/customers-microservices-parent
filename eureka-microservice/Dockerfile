FROM openjdk:8-jdk-slim

VOLUME /tmp

EXPOSE 8090

ARG JAR_FILE=target/eureka-microservice-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} eureka-microservice.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-microservice.jar"]