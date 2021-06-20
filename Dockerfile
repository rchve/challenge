## Stage 1 Building using gradle
FROM gradle:6.8.3-jdk11 as builder
WORKDIR /usr/src/app/
COPY . .
## Building the application using gradle. Skipping tests to reduce time.
RUN gradle --no-daemon clean build -x test

## Stage 2 Build image using openjdk
FROM openjdk:11.0.11-jdk
WORKDIR /work/
# Configure the JAVA_OPTIONS, you can add -XshowSettings:vm to also display the heap size.
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

COPY --from=builder /usr/src/app/build/challenge-*-runner.jar /work/
EXPOSE 8080
USER 1001

ENTRYPOINT [ "java", "-jar", "/work/challenge-*-runner.jar" ]
