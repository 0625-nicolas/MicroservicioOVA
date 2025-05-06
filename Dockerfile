FROM openjdk:23
LABEL authors="gutin"
WORKDIR /app
COPY target/OVA-service-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java","-jar", "OVA-service-0.0.1-SNAPSHOT.jar"]