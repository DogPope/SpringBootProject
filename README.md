# This is the information page for my Spring Boot Application

[Here!](https://youtu.be/31KTdfRH6nY?t=8660)

## What I've learned
- The Spring Boot Framework(Annotations, etc.)
- Optionals in Java
- Records in Java
- How to hide Easter eggs in your API.
- Writing a Bash script to test the API endpoints.
- Java Jakarta validation library.
- Convention over Configuration layouts.
- Spring Boot Database Integration in this case, MariaDB.
- Application Properties - [Found Here](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)

## To-Do list
- [ ] Fix the Date Validation to prevent user from putting in a Death date that occurs after Date of Birth.
- [x] Fix Insert Statement to work properly.
- [x] Edit the Country class file to tidy up after ChatGPT.
- [x] Deal with database integration through JDBC and MariaDB
- [ ] Set up a jar file for all to be Dockerized.

## Running the Project
Steps:
- Download [Docker](https://www.docker.com/products/docker-desktop/) for using the MariaDB container.
- Run the Application in Intellij or using ```./mvnw spring-boot:run``` from terminal where pom.xml is located.