FROM openjdk:8-jdk-slim

VOLUME /tmp

EXPOSE 8092

ARG JAR_FILE=target/gateway-microservice-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} gateway-microservice.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway-microservice.jar"]