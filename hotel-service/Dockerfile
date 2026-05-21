FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /workspace
COPY . .
RUN mvn -pl hotel-service -am package -DskipTests

FROM bellsoft/liberica-openjdk-debian:11
RUN apt-get update \
    && apt-get install -y --no-install-recommends wget \
    && rm -rf /var/lib/apt/lists/*
COPY --from=build /workspace/hotel-service/target/*.jar hotel-service.jar
ENTRYPOINT ["java","-jar","/hotel-service.jar"]
