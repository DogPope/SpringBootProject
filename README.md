# This is the information page for my Spring Boot Application

# ***IMPORTANT: TO STOP THE MYSQL PROCESS ON 3306, FOLLOW [THIS THREAD](https://stackoverflow.com/questions/68065284/specified-port-3306-is-already-in-use-when-installing-mysql) ON STACK OVERFLOW. Use Windows key + 'R', Find services.msc and stop mysql from there.***

# To-Do List
- [x] Tie Instruments to Array List and implement instruments either via Triggers or Programmatically.
- [x] To avoid problems accessing ports in college, refactor database to PostgreSQL instead.
- [ ] Refactor the Application to include "Views".
- [x] Refactor the Application to cater to multiple types of musicians.
- [x] Display Musician info correctly, with corresponding instruments.
- [x] Refactor the Class names and add a Design Pattern to it.(Factory)
- [ ] Refactor the Bash Script to hit the correct API endpoints.
- [ ] Record the remaining Design patterns and implement them into report(Sidecar, Singleton)
- [ ] Refactor tests to work properly.

## Links / Resources
- [PlantUML Reference Guide](https://pdf.plantuml.net/1.2020.22/PlantUML_Language_Reference_Guide_en.pdf)
- [For another day, Dan Vega has a project that implements Observability.](https://github.com/danvega/observability)
- [This may be why the views aren't working](https://stackoverflow.com/questions/52505514/spring-boot-404-when-trying-to-load-a-html-file-using-thymeleaf)

## Branching Strategy
For this project, the branching strategy is going to concern two major branches. The "Architecture" branch will concern major refactorings and implement them into the program.
The "Master" Branch will be a representation of the starting point for this project, a Spring Boot Application that ties basic "Composer" objects to their "Pieces".

## Project Description
This project is an application made in the Spring Boot Framework for the Java Programming language.
It is a REST API(MVC Controller) using the CRUD repository format. The API handles data on Musicians, Compositions and their Instruments.
attributed to them. It uses MariaDB as its database, in this case, held in a separate Docker container, referred to as a Sidecar design pattern, mapped to
local port 3306. The application itself is hosted at localhost on Port 5000.

## Running the Project
Steps:
- Download [Docker](https://www.docker.com/products/docker-desktop/) for using the MariaDB container.
- Run the Docker Compose file using ```docker-compose up -d``` in the terminal.
- For Windows, run the Application in Intellij or using ```.\mvnw spring-boot:run``` from terminal where pom.xml is located.
- If using a Linux based System, use ./ for Bash terminals instead of .\ to run the script. ```./mvnw spring-voot:run```
- When the project is running, visit [Localhost](http://localhost:5000/api/musicians) to start viewing some already
seeded data on musicians. Alternatively, visit [Localhost](http://localhost:5000/api/compositions) to view the compositions
already available in the system.
- The HTTP file accompanying this project in the main directory can be used to view requests being sent to interact
with the system.
Testing API interactions can be done using the HTTP file if your environment allows for running it. If not, the Bash
script included can be called using ```./testEndpoints.sh```

### Results
When viewed, the browser displays the following:
![Picture of Browser with musician Data](Screenshots/composers.png)
![Picture of Browser with composition Data](Screenshots/pieces.png)

## Running Tests - Dunno if this is that relevant.
To run the Integration tests accompanying this application, the Docker container has to be run separately from the main application to prevent data from being inserted prematurely.
The database checks total entries, for example, which numbers would be out if the app was run and sample data added before testing.

## Note
Martha Argerich causing problems, due to her still being alive. The date of death returns null, causing problems for the RowMapper.
@RestController
@Controller
@Beans
@Repository - MusicianRepository Interface + JDBCClientMusicianRepository
@SpringBootApplication - Main.