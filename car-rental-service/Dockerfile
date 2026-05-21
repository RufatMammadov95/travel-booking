FROM bellsoft/liberica-openjdk-debian:11
RUN apt-get update \
    && apt-get install -y --no-install-recommends wget \
    && rm -rf /var/lib/apt/lists/*
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} car-rental-service.jar
ENTRYPOINT ["java","-jar","/car-rental-service.jar"]
