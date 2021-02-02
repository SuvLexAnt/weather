FROM openjdk:8-jdk-alpine
MAINTAINER Suvorov Lex <AlexandrTheSlayer@mail.ru>

EXPOSE 8080 7001
ARG JAR=build/libs/*.jar
ADD ${JAR} app.jar

ENV DATABASE_HOST value
ENV DATABASE_PORT value
ENV POSTGRES_USER value
ENV POSTGRES_PASSWORD value
ENV POSTGRES_DB value

CMD ["java", "-jar", "app.jar"]
