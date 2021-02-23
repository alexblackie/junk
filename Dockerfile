FROM maven:3.6-jdk-13 as builder
ADD . /app
WORKDIR /app
# We need some properties filled for the beans to be initialized properly; the values don't matter.
RUN cp src/main/resources/application.yml.example src/main/resources/application.yml && \
	mvn package

FROM openjdk:13-alpine
COPY --from=builder /app/target/junk-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar", "--spring.config.location=/etc/junk.yml"]
