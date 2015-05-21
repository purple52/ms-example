# Microservices Example

[![Build Status](https://travis-ci.org/purple52/ms-example.svg?branch=master)](https://travis-ci.org/purple52/ms-example)

This is implemented using Java 7 and Spring Boot with Maven for building the project. Continuous integration is done using Travis CI.

Build using Maven, eg:

    mvn clean package

Run using the Java command, eg:

    java -Dserver.port=8080 -jar user-api/target/user-api-1.0-SNAPSHOT.jar
    java -Dserver.port=8081 -jar connection-api/target/connection-api-1.0-SNAPSHOT.jar

## Testing

You can test the user API using Curl:

    $ curl -H "Content-Type: application/json" -X POST -d '{"name": "username one"}' http://localhost:8080/users
    {"id":1,"name":"username one"}
    $ curl http://localhost:8080/users/1
    {"id":1,"name":"username one"}

and the connection API:

    $ curl -H "Content-Type: application/json" -X POST -d '{"id": 2}' http://localhost:8081/users/1/connections
    $ curl -H "Content-Type: application/json" -X POST -d '{"id": 3}' http://localhost:8081/users/1/connections
    $ curl http://localhost:8081/users/1/connections                                                           
    [2,3]
    $ curl http://localhost:8081/users/2/connections
    [1]

## Notes

* Tests demonstrate unit testing for non-Spring classes and a simple integration test for the API applictaion itself.
* The module structure of the project has been designed to keep the Spring applications separate from the business logic.
This make it much clearer what code is reusable, and which code is solely for wiring up the Spring applications.
* The classes used for the internal model representation are not the same as the external API model classes. This allows
the two to be altered independently.