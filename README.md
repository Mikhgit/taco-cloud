# Taco Cloud

Taco Cloud is a microservice application using Spring Boot with Java 14 and based on:
* [Spring in Action, Fifth Edition](https://www.manning.com/books/spring-in-action-fifth-edition)
* [Spring Microservices in Action - Second Edition](https://www.manning.com/books/spring-microservices-in-action-second-edition)


## Architecture Overview
[](url "title")
<img src="scheme.png">

## Technology
1. **Eureka server** is service discovery.
2. **Spring Cloud Config** is an application that manages services configuration information using GitHub-based repository.
3. **Vault** is storage of sensitive data.
4. **Spring Cloud Gateway** provides support for routing and filtering incoming requests to microservices.
5. **KeyCloak** is authentication and authorization server.
6. **MongoDB** database used to hold the data.
7. **RabbitMQ** message bus to transport messages between services.
8. **Spring Boot Admin** is a web application, used for managing and monitoring Spring Boot applications.
9. **ELK Stack (Elasticsearch, Logstash, Kibana)** is a popular suite of tools for ingesting, viewing, and managing log files.
10. **Zipkin** is a distributed tracing system.
11. **Grafana & Prometheus** are two monitoring tools.
12. **Docker**