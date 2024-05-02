# Please Water Me

605.607 class project application

## Required software

This project was developed with the following versions of software:

- Java 11
- Maven 3.8.8
- Spring Tool Suite 4

## Required setup

### Database

1. Install MySQL on your computer
1. Create a MySQL database for this application
1. Update the following in src/main/resources/application.properties: spring.datasource.url, spring.datasource.username, spring.datasource.password

## To build and run

- `mvn clean spring-boot:run`

Access the application at http://localhost:8080/