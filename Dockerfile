FROM maven:3.6-jdk-13 as builder
ADD . /app
WORKDIR /app
RUN mvn package

FROM openjdk:13-alpine
COPY --from=builder /app/target/junk-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar", "--spring.config.location=/etc/junk.yml"]
