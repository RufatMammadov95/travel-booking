FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /workspace
COPY . .
RUN mvn -pl car-rental-service -am package -DskipTests

FROM bellsoft/liberica-openjdk-debian:11
RUN apt-get update \
    && apt-get install -y --no-install-recommends wget \
    && rm -rf /var/lib/apt/lists/*
COPY --from=build /workspace/car-rental-service/target/*.jar car-rental-service.jar
ENTRYPOINT ["java","-jar","/car-rental-service.jar"]
