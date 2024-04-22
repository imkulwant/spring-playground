# Spring Cloud Gateway Example

This repository contains a simple example project demonstrating how to use Spring Cloud Gateway to route incoming requests to different downstream services based on the API path.

## Overview

In this example, we have three downstream services:

#### Service 1 
- A Python HTTP server listening on port 8081, handling requests to
  - **GET /resource** `curl http://localhost:8081/resource`
  - **POST /resource** `curl -X POST -H "Content-Type: application/json" -d '{"name": "John", "age": 30}' http://localhost:8081/resource`

#### Service 2
- A Python HTTP server listening on port 8082, handling requests to
    - **GET /resource** `curl http://localhost:8082/resource`
    - **POST /resource** `curl -X POST -H "Content-Type: application/json" -d '{"name": "John", "age": 30}' http://localhost:8082/resource`

#### Service 3
- A Python HTTP server listening on port 8083, handling requests to
    - **GET /resource** `curl http://localhost:8083/resource`
    - **POST /resource** `curl -X POST -H "Content-Type: application/json" -d '{"name": "John", "age": 30}' http://localhost:8083/resource`

## How to Run

- Clone this repository:
- Start the Python HTTP servers
    ```bash
    cd src/main/python
    python service-one.py
    python service-two.py
    python service-three.py
    ```
- Start the Spring Cloud Gateway: ```mvn spring-boot:run```

## Testing
Once all services are running, you can test the routing by sending requests to the gateway:

- Routing to Service 1: ```curl http://localhost:8080/service/resource```
- Dynamic Routing to Service 1: ```curl -X POST -H "Content-Type: application/json" -d '{"metadata": {"countryCode" : "UK"}}' http://localhost:8080/service/resource```
- Dynamic Routing to Service 2: ```curl -X POST -H "Content-Type: application/json" -d '{"metadata": {"countryCode" : "NL"}}' http://localhost:8080/service/resource```
- Routing to Service 3: ```curl -X POST -H "Content-Type: application/json" -d '{"metadata": {"countryCode" : "US"}}' http://localhost:8080/service-three/api```


# Notes
- https://www.baeldung.com/spring-cloud-gateway
- https://www.baeldung.com/spring-cloud-custom-gateway-filters



