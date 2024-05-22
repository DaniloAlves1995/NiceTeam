# Nice Team API

## About

This API simulates a simple backend for a soccer team management system, specifically for the Nice football team in the league.
The objective is to facilitate operations related to team, particularly in the context of the transfer market.

Points of the Application:

* **Manage Teams and Players**: It allows to manage information related to football teams and their player.
* **Provide a REST API**: Expose RESTful endpoints to allow easy interaction with the application.
* **Pagination and Sorting**: Enable pagination and server-side sorting of the team list to handle large datasets.

## Architecture
This solution uses Spring Boot and Spring Data JPA, which provides an abstraction layer over the Hibernates and simplifying database interactions.
The database used is PostgreSQL, a relational database easy to use with good integration to spring and common used in the market.

The architecture used is the Layered Architecture. A common architectural pattern for building enterprise applications, which each logic part of the system is separated 
in different layers. The 4 layers used in this solution are listed bellow:

1. Presentation Layer: Handles incoming HTTP requests, guide them to service calls, and returns the responses to the clients.
In this solution this layer is represented by the class `TeamController.java`.
2. Service Layer: It processes the data received from the controllers, applies business rules, and interacts with the data access layer. In this solution it's the `TeamService.java`.
3. Data Access Layer: This layer uses Spring Data JPA to perform CRUD operations on the database. This layer containers the 
interfaces `TeamRepository.java` and `PlayerRepository.java` in this solution.
4. Database Layer: Stores the application's data. The database used in this application is `PostgreSQL` and it's managed through JPA entities.

## Instalation Guide

Before follow a specific instalation tool, you need to clone this repository in your local computer:
```bash
git clone https://github.com/DaniloAlves1995/NiceTeam.git
```
Case you don't have git installed, please follow the instructions [install Git.](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

### Running by docker

You just need to enter the root project folder and run the docker compose:
```bash
cd NiceTeam
docker compose up
```
Case you don't have docker installed, please follow the instructions [install Docker.](https://docs.docker.com/engine/install/)

### Running by maven

For running the application by maven, you still need to have a PostgreSQL running in your localhost.
You can run the docker command bellow to run a postgreSQL image with the configurations needed by the API:
```bash
docker run -p 5432:5432 -v pg_db:/var/lib/postgresql/data -e POSTGRES_USER=nice_team -e POSTGRES_PASSWORD="cfz{O3(F9me#TXj" -e POSTGRES_DB=football_league postgres:14.5
```
Once the container is running you can up the API:

Linux users:
```bash
./mvnw spring-boot:run
```

Windows users:
```bash
./mvnw.cmd spring-boot:run
```

### Running by standalone jar

This repository already provides a standalone jar to test the API. You need first run the PostgreSQL container 
from the previous step and then run the standalone jar:

```bash
cd app
java -jar NiceTeam-0.0.1-SNAPSHOT.jar
```

Running by maven or standalone requires that you have Java installed and the JAVA_HOME variable set.

Independent of the tool chosen for running the application, you'll have an API running on the 8080 port, which can be checked by the 
link: http://localhost:8080/api/teams

## Testing

With the application running on the 8080 port, you can apply requests to the endpoints and check the results. This repository provides a Postman collection with some requests ready to be executed.

For testing the requests on Postman, you need to import the collection following:

``File Menu > Import...`` and then, select the file `NiceTeam.postman_collection.json` inside the folder /postman-colletion.

After this, you'll have some requests to check the API functionalities. You can select the requests one by one and hit the ``Send`` button.