# AlphaCalcolutions
The 2. semester exam project of the computer science education at [KEA](https://kea.dk/)

We were given the assignment to develop a project management software for the company
[Alpha Solutions](https://www.alpha-solutions.com/da).

The system should aleast include the follwing features:
- Create a data model for project and time dimensions. (Create the right tables in your database)
- Create a webinterface for CRUD operations, of project, tasks and users.
- Time distribution on workdays

Supplementary features, not required:
- Project visualization via a GANTT diagram.
- Introduct resource types and supply an overview of how many hours each resource work each day.
- Overview of the work load on each employee on each day of the project.

## Progress/Implementation:
- [x] Create a data model for project and time dimensions. (Create the right tables in your database)
    - We have created a MySQL database with the following tables:
        - user
        - task
        - project
        - user_title
        - user_has_project
        - user_has_task
        - task_has_dependency
- [x] Create a webinterface for CRUD operations, of project, tasks and users.
    - We used spring MVC to set up the backend and Thymeleaf as a template engine.
- [x] Time distribution on workdays

- [x] Project visualization via a GANTT diagram.
- [x] Introduct resource types and supply an overview of how many hours each resource work each day.
    - We used a timeline diagram to display the workload on each resource.
- [x] Overview of the work load on each employee on each day of the project.
    - We again use a timeline diagram to visualize the workload on each employee

## Run
1. Setup Database

Source the `database.sql` file in MySQL, to create the `alpha_calcolutions` database.
Can be done with this command `source database.sql` while loggedin to the MySQL server from the folder where `database.sql` is located.

2. Create application.properties

Navigate to `AlphaCalcolutions/alphaCalcolutions/src/main/resources` and create a new file called `application.properties`.

Fill it with the following:
```
url=jdbc:mysql://localhost:3306/alpha_calcolutions?serverTimezone=UTC
user=[username]
password=[password]
```
Replace `[username]` and `[password]` with the username and password of the MySQL user you used to create the database.

3. Run the application

Navigate to `AlphaCalcolutions/alphaCalcolutions` and execute:

`./mvnw spring-boot:run` - Linux

`mvnw.cmd spring-boot:run` - Windows
 
Finally navigate to [localhost:8080](http://localhost:8080) to use the website.

Regular user credentials: 
Username = `Frje` 
Password = `Frje` 

Admin user credentials: 
Username = `Heha`  
Password = `Heha`  

## Test
Navigate to `AlphaCalcolutions/alphaCalcolutions` and execute:

`./mvnw test` - Linux

`mvnw.cmd test` - Windows


## Contributors
[JoergenKoeldal](https://github.com/JoergenKoeldal)

[PVGlimoe](https://github.com/PVGlimoe)

[MatiasStorm](https://github.com/MatiasStorm)
