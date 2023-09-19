
# Build stage
FROM maven:3.9-amazoncorretto-20-debian AS build
WORKDIR /app
COPY . .
RUN mvn clean package -Pprod -DskipTests

# Package stage
FROM openjdk:20-jdk-slim
WORKDIR /app
COPY --from=build /app/target/quipu-0.0.1-SNAPSHOT.jar app.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]