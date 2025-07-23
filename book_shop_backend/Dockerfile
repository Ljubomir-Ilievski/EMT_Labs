#Stage 1 - build with maven, create jar file
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

#Stage 2 - run without maven
FROM eclipse-temurin:21.0.7_6-jre-ubi9-minimal


WORKDIR /app
COPY --from=build /app/target/*.jar bookStore.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bookStore.jar"]
