# challenge project

### Pre-requisites
JDK 11

### Technologies Used
- Quarkus as core framework (JAX-RS for creating rest endpoints)
- Jackson (for JSON marshalling/unmarshalling)
- Lombok (for generating builder and immutable classes)
- Microprofile (rest-client, health)
- JIB (for generating docker images)
- Junit5 (Testing framework)
- Mockito (Mocking during testing)
- Wiremock (Mock server used for integration tests)

### Testing the application
You can run all the application tests (unit and integration) using:
```shell script
./gradlew test
```
When Integration tests run wiremock is used as mockserver to provide backend api responses 

### Running the application in dev mode
You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

### Packaging and running the application
The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

### Packaging the application as docker image
This step requires docker to be running.  The application can be packaged as docker container using:
```shell script
./gradlew clean build -Dquarkus.container-image.build=true
docker images
```
on successful completion the docker image in the loaded on docker and can be run.

### Additional endpoints
- Health endpoint (http://localhost:8080/q/health) is available for health checks
- OpenAPI 3.0 (http://localhost:8080/q/openapi) - Generated swagger specs for the application
