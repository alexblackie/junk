FROM maven:3.8-openjdk-18-slim as builder
ADD . /app
WORKDIR /app
# We need some properties filled for the beans to be initialized properly; the values don't matter.
RUN cp src/main/resources/application.yml.example src/main/resources/application.yml && \
	mvn package

FROM openjdk:18-slim
COPY --from=builder /app/target/junk-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar"]
