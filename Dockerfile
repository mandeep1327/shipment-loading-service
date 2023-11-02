FROM ghcr.io/graalvm/graalvm-ce:22.1.0
ARG JAR_FILE=target/shipment-loading-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

