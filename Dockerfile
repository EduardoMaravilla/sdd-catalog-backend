FROM eclipse-temurin:22-jdk-alpine
LABEL authors="Eduardo Maravilla"
COPY target/*.jar app.jar
EXPOSE 9696:9595
ENTRYPOINT ["java","-jar","/app.jar"]