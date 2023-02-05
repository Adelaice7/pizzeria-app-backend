FROM gradle:latest
COPY . /tmp
RUN gradle build --no-daemon

FROM openjdk:19-jdk-alpine
EXPOSE 8080
EXPOSE 9090
RUN mkdir pizzeria-app
COPY ../../build/libs/pizzeria-server-1.0.0.jar pizzeria-app/pizzeria-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/pizzeria-app/pizzeria-server-1.0.0.jar"]