FROM openjdk:21-jdk-slim
WORKDIR /app
COPY currency-1.1.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]