# Badges

[![CircleCI](https://circleci.com/gh/abalzan/ecommerce-microservices.svg?style=svg)](https://circleci.com/gh/abalzan/ecommerce-microservices)

[![codecov](https://codecov.io/gh/abalzan/ecommerce-microservices/branch/master/graph/badge.svg)](https://codecov.io/gh/abalzan/ecommerce-microservices)

<a href="https://codeclimate.com/github/abalzan/ecommerce-microservices/maintainability"><img src="https://api.codeclimate.com/v1/badges/93e3fea017ac2189d186/maintainability" /></a>

# Run application using docker

```$ mvn clean install```
OR
```$ mvn package -B```

Run your containers:

```$ docker-compose up```
OR
```$ docker-compose up --build  --force-recreate```

Check all containers are up and running:

```$ docker ps```

For stopping our stack:

```$ docker-compose stop```

# Database Setup
In this project I am using flyway to create the database, for this I just create a separated project
 responsible just to create the tables and some initial data.
```http://localhost:16600``` this is just a random port and it doesn't make any difference as after 
the creation of the database the application shutdown.

# Gateway Urls
| Application | URL |
| --- | --- |
| product-api | ```http://localhost:8888/product/products``` |
| category-api  | ```http://localhost:8888/category/categories``` |
| user-api  | ```http://localhost:8888/user/users``` |
| address-api  | ```http://localhost:8888/address/addresses``` |


# Application URLs
To see all the configured endpoints please check Swagger documentation:

| Application | URL |
| --- | --- |
| product-api | ```http://localhost:18000/products``` |
| category-api  | ```http://localhost:18100/categories``` |
| user-api  | ```http://localhost:18200/users``` |
| address-api  | ```http://localhost:18300/addresses``` |

### Swagger Urls

| Application | URL |
| --- | --- |
| product-api | ```http://localhost:18000/swagger-ui.html``` |
| category-api  | ```http://localhost:18100/swagger-ui.html``` |
| user-api  | ```http://localhost:18200/swagger-ui.html``` |
| address-api  | ```http://localhost:18300/swagger-ui.html``` |


### Eureka
http://localhost:8761/

### Configuration files from git
The files are retrieved from git uri passed into the:
```
spring.cloud.config.server.git.uri
```
The configuration files can be retrieved from github, using the urls:
| Application | URL |
| --- | --- |
| product-api | ```http://localhost:9000/product-api/dev``` |
| category-api  | ```http://localhost:9000/category-api/dev``` |
| user-api  | ```http://localhost:9000/user-api/dev``` |
| address-api  | ```http://localhost:9000/address-api/dev``` |
| gateway-service  | ```http://localhost:9000/gateway-service/dev``` |

### Actuator
Each project has actuator configured, follow the Urls:

| Application | URL |
| --- | --- |
| product-api | ```http://localhost:8091/actuator``` |
| category-api  | ```http://localhost:8092/actuator``` |
| user-api  | ```http://localhost:8093/actuator``` |
| address-api  | ```http://localhost:8094/actuator``` |
