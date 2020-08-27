FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR=target/*.jar
ADD ${JAR} app.jar
ENTRYPOINT ["java -jar /app.jar"]