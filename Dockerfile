FROM openjdk:21

LABEL authors="matheus"

WORKDIR app

COPY build/libs/santander-dev-week-2024-0.0.7-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]