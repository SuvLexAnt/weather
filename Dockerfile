#CMD gradle -Pflyway.user=${POSTGRES_USER} -Pflyway.schemas=public -Pflyway.password=${POSTGRES_PASSWORD} -Pflyway.url=jdbc:postgresql://${DATABASE_HOST}:${DATABASE_PORT}/${POSTGRES_DB} flywayMigrate
FROM openjdk:8-jdk-alpine
MAINTAINER Suvorov Lex <AlexandrTheSlayer@mail.ru>

EXPOSE 8080
ARG JAR=build/libs/*.jar
ADD ${JAR} app.jar
CMD ["java", "-jar", "app.jar"]
