# Pegar o jdk 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY build/libs/agendador-tarefas-api-0.0.1-SNAPSHOT.jar /app/agendador-tarefas-api.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/agendador-tarefas-api.jar"]