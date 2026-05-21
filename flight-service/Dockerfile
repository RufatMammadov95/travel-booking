FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /workspace
COPY . .
RUN mvn -pl flight-service -am package -DskipTests

FROM bellsoft/liberica-openjdk-debian:11
RUN apt-get update \
    && apt-get install -y --no-install-recommends wget \
    && rm -rf /var/lib/apt/lists/*
COPY --from=build /workspace/flight-service/target/*.jar flight-service.jar
ENTRYPOINT ["java","-jar","/flight-service.jar"]
