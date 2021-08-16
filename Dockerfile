## Stage 1 : build with maven builder image with native capabilities
FROM quay.io/quarkus/centos-quarkus-maven:21.0.0-java11 AS builder
COPY pom.xml /usr/src/app/
COPY src /usr/src/app/src
USER root
RUN chown -R quarkus /usr/src/app
USER quarkus
RUN mvn -f /usr/src/app/pom.xml clean package

## Stage 2 : create the docker final image
FROM openjdk:11.0.10-jre-slim as runtime
COPY --from=builder /usr/src/app/target/*.jar /app/app.jar
COPY --from=builder /usr/src/app/src/main/resources/application.properties /app/config/application.properties
EXPOSE 8080

CMD ["java","-Xms128M","-Xmx512M","-jar","/app/app.jar"]
