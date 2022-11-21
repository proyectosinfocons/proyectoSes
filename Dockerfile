FROM mongo:latest

RUN apt-get update -y
RUN apt-get remove mongodb-org -y
RUN apt-get install mongodb -y
RUN mkdir -p ./data/db
RUN service mongodb start -y
EXPOSE 27017


FROM maven:3.6.3-jdk-11-openj9 AS MVN_M2
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
RUN ls /build/target/
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=MVN_M2 /build/target/proyectoSes-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "proyectoSes-0.0.1-SNAPSHOT.jar"]
