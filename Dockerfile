FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY build/libs/bot.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]