![build](https://travis-ci.org/narendrasoni1989/dataloader.svg?branch=master)
[![codecov](https://codecov.io/gh/narendrasoni1989/dataloader/branch/master/graph/badge.svg?token=Y5QH4WI4K6)](https://codecov.io/gh/narendrasoni1989/dataloader)

# Getting Started

###  Dataloader
This is a simple project developed using Spring Batch to read data from csv file and save it in database.
This project is using mysql as database.  Project is using 	`transaction` to manage the consistency of data. If any error or exception occurs while it is in progress then previously loaded data would be rollback.

Project is using java11.

## Build 
`./gradlew clean build`

## Run
`java -jar build/libs/dbloader-0.0.1-SNAPSHOT.jar <command-name>`

Currently this project is only supporting only one command which is 	`LOADER` but it can be easily extended to support multiple commands and it's corresponding implementation.

## Running the LOADER command 
`java -jar build/libs/dbloader-0.0.1-SNAPSHOT.jar LOADER`

## How to set datasource ?
refer to `application.properties` file in `src/main/resources` folder, provide your own datasource endpoint and create schema using `schema.sql`  file from `src/test/resources` folder.


## Enhancements
1. Containerize the application using docker to make it easy to promote code in different environment.
2. Current project is just inserting the data. If integrity constraint are not met then it throws Exceptions. If there are valid business case around it then extend the load to also support update/read/delete operations.


### Note :
 Make sure you have Docker installed in your machine. This project is using `testcontainer` for unit testing so start docker engine before you build this project. Very first time when you build it, it will be slow because it will download the container and prepare environment.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.0/gradle-plugin/reference/html/#build-image)
* [Testcontainers MySQL Module Reference Guide](https://www.testcontainers.org/modules/databases/mysql/)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#howto-batch-applications)
* [Testcontainers](https://www.testcontainers.org/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)