# Microservices Example

[![Build Status](https://travis-ci.org/purple52/ms-example.svg?branch=master)](https://travis-ci.org/purple52/ms-example)

This is implemented using Java 7 and Spring Boot with Maven for building the project. Continuous integration is done using Travis CI.

**Note**: This solution does not implement the problem specification exactly. Specifically, the Connections API does not
provide endpoints for creating and retrieving users. This is done deliberately to highlight a point for discussion regarding
abstraction of persistence. In order to demonstrate two services communicating, when a connection is created a call is made 
to the User API to check the users exist. It would be simple to proxy through user CRUD calls via the Connections API,
but this raises a number of issues around complexity, responsibility and reliability. In short, if the underlying storage is decoupled from
the exposed microservice, then a more complex solution is required to guarantee persistence.

## Build and Run

Build using Maven, eg:

    mvn clean package

Run using the Java command, eg:

    java -jar user-api/target/user-api-1.1-SNAPSHOT.jar --server.port=8080 
    java -jar connection-api/target/connection-api-1.1-SNAPSHOT.jar --server.port=8081 --userApiBaseUrl=http://localhost:8080

Or download the latest .jar files from the [Releases](https://github.com/purple52/ms-example/releases) page and run as above with the downloaded filename.

## Testing

You can test the user API using Curl:

    $ curl -H "Content-Type: application/json" -X POST -d '{"name": "username one"}' http://localhost:8080/users
    {"id":1,"name":"username one"}
    $ curl -H "Content-Type: application/json" -X POST -d '{"name": "username two"}' http://localhost:8080/users
    {"id":2,"name":"username two"}
    $ curl -H "Content-Type: application/json" -X POST -d '{"name": "username three"}' http://localhost:8080/users
    {"id":3,"name":"username three"}
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

* Tests demonstrate unit testing for non-Spring classes and a simple integration test for the API application itself.
The tests are not exhaustive.
* In places, the implementation is quick and dirty to produce a working end-to-end example.
* The module structure of the project has been designed to keep the Spring applications separate from the business logic.
This make it much clearer what code is reusable, and which code is solely for wiring up the Spring applications.
* The classes used for the internal model representation are not the same as the external API model classes. This allows
the two to be altered independently.