# Cities backend application
Cities backend is a Spring boot application for managing cities.

# Database
Application is working with PostgreSql database.
```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:cities}
```

# How to run project locally

### Prerequisites
- [Docker](https://www.docker.com/get-started/)
- [Docker-compose](https://docs.docker.com/compose/install/)
- [Java version 17](https://www.azul.com/downloads/?package=jdk#download-openjdk)
- [Maven](https://maven.apache.org/install.html)

### 
Execute following command from the root folder of the current project
```command
sh build.sh -p default
```

Wait a minute while script will be executed and run up all services. Then you can start working with it. 

# Api Documentation

We are using Swagger 2 to provide documentation for our API. 
It is available under the following url if you are running service on the local machine:

```swagger api
http://localhost:9000/api/swagger-ui/index.html
```
