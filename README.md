# Badges

[![CircleCI](https://circleci.com/gh/abalzan/ecommerce-microservices.svg?style=svg)](https://circleci.com/gh/abalzan/ecommerce-microservices)

[![codecov](https://codecov.io/gh/abalzan/ecommerce-microservices/branch/master/graph/badge.svg)](https://codecov.io/gh/abalzan/ecommerce-microservices)

<a href="https://codeclimate.com/github/abalzan/ecommerce-microservices/maintainability"><img src="https://api.codeclimate.com/v1/badges/93e3fea017ac2189d186/maintainability" /></a>

# Application URLs
To see all the configured endpoints please check Swagger documentation:

product-api 
```http://localhost:18000/products```

categories-api 
```http://localhost:18100/products```

### Swagger Urls

product-api ```http://localhost:18000/swagger-ui.html```

category-api ```http://localhost:18100/swagger-ui.html```

### Eureka
http://localhost:8761/

### Configuration files from git
The files are retrieved from git uri passed into the:
```
spring.cloud.config.server.git.uri
```
The configuration files can be retrieved from github, using the urls:
http://localhost:9000/product-api/dev

http://localhost:9000/category-api/dev

### Actuator
Each project has actuator configured, follow the Urls:

product-api ```http://localhost:8091/actuator```

category-api ```http://localhost:8092/actuator```
