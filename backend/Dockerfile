FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean install -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/foreal-project-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
