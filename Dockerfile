FROM openjdk:8-jdk-alpine
MAINTAINER Suvorov Lex <AlexandrTheSlayer@mail.ru>

EXPOSE 8080
ARG JAR=build/libs/*.jar
ADD ${JAR} app.jar
CMD ["java", "-jar", "app.jar"]
