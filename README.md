# Checkout System for Kantox 

 This System is a rest API built with Spring boot using Maven as a dependency manager,  
Also use Open api to describe, produce, consume, and visualize the services.
The services can be tested in http://localhost:8080/swagger-ui/index.html

## Starting 🚀

 You can clone or download a copy o this project, using git functions
 Then simply you need open the project with you IDE and follow the instructions to build or run test,  
 Maven should download all the needed dependencies.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in 
the `com.kantox.checkout.CheckoutApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## Running the tests ⚙️

```shell
mvn test
```

This command also will run a report of coverage that can be found in
/target/site/jacoco/index.html

## Technologies 🛠️

* [SpringBoot]((https://spring.io/projects/spring-boot)) - The main framework
* [OpenApi](https://springdoc.org/) - For documentation
* [Maven](https://maven.apache.org/) - Dependency handler
* [H2](https://www.h2database.com/) - Used DB
* [Jacoco](https://www.jacoco.org/) - For coverage report

## Contributor 🖇️

Melisa Branfman
