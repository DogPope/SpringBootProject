# This is the information page for my Spring Boot Application

# ***IMPORTANT: TO STOP THE MYSQL PROCESS ON 3306, FOLLOW [THIS THREAD](https://stackoverflow.com/questions/68065284/specified-port-3306-is-already-in-use-when-installing-mysql) ON STACK OVERFLOW. Use Windows key + 'R', Find services.msc and stop mysql from there.***

## Links / Resources
- [PlantUML Reference Guide](https://pdf.plantuml.net/1.2020.22/PlantUML_Language_Reference_Guide_en.pdf)

## Project Description
This project is an application made in the Spring Boot Framework for the Java Programming language.
It is a REST API using the CRUD repository format. The API handles data on piano composers and various pieces
attributed to them. It uses MariaDB as its database, in this case, held in a separate Docker container, mapped to
local port 3306. The application itself is hosted at localhost on Port 5000.

## Running the Project
Steps:
- Download [Docker](https://www.docker.com/products/docker-desktop/) for using the MariaDB container.
- Run the Docker Compose file using ```docker-compose up -d``` in the terminal.
- For Windows, run the Application in Intellij or using ```.\mvnw spring-boot:run``` from terminal where pom.xml is located.
- If using a Linux based System, use ./ for Bash terminals instead of .\ to run the script. ```./mvnw spring-voot:run```
- When the project is running, visit [Localhost](http://localhost:5000/api/composers) to start viewing some already
seeded data on composers. Alternatively, visit [Localhost](http://localhost:5000/api/pieces) to view the compositions
already available in the system.
- The HTTP file accompanying this project in the main directory can be used to view requests being sent to interact
with the system.
Testing API interactions can be done using the HTTP file if your environment allows for running it. If not, the Bash
script included can be called using ```./testEndpoints.sh```

### Results
When viewed, the browser displays the following:
![Picture of Browser with composer Data](Screenshots/composers.png)
![Picture of Browser with piece Data](Screenshots/pieces.png)

## Running Tests
To run the Integration tests accompanying this application, the Docker container has to be run separately from the main application to prevent data from being inserted prematurely.
The database checks total entries, for example, which numbers would be out if the app was run and sample data added before testing.