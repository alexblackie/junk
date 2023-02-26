FROM ibm-semeru-runtimes:open-17-jdk as builder
RUN apt-get update && apt-get install -y --no-install-recommends maven
ADD . /app
WORKDIR /app
# We need some properties filled for the beans to be initialized properly; the values don't matter.
RUN cp src/main/resources/application.yml.example src/main/resources/application.yml && \
	mvn package -Dmaven.test.skip=true

FROM ibm-semeru-runtimes:open-17-jre
COPY --from=builder /app/target/junk-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar"]
