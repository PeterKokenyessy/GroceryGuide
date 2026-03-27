# --------- Dev Stage ---------
FROM maven:3.9.9-amazoncorretto-23-debian-m AS dev
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.profiles=dev"]

# --------- Runtime Stage ---------
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app
COPY app.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
