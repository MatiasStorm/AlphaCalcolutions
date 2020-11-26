# AlphaCalcolutions

## Run
1. Setup Database
Source the `database.sql` file in MySQL, to create the `alpha_calcolutions` database.
Can be done with this command `source database.sql` while loggedin to the MySQL server from the folder where `database.sql` is located.

2. Create application.properties
Navigate to `DateNRate/DateNRate/src/main/resources` and create a new file called `application.properties`.

Fill it with the following:
```
url=jdbc:mysql://localhost:3306/alph_calcolutions?serverTimezone=UTC
user=[username]
password=[password]
```
Replace `[username]` and `[password]` with the username and password of the MySQL user you used to create the database.

3. Run the application
Navigate to `AlphaCalcolutions/alphaCalcolutions` and execute:
`./mvn spring-boot:run` - Linux
`./mvnw spring-boot:run` - Windows
 
Finally navigate to [localhost:8080](http://localhost:8080) to use the website.

The admin page, where one can delete users, is located at [localhost:8080/admin](http://localhost:8080/admin).

## Test
Navigate to `AlphaCalcolutions/alphaCalcolutions` and execute:
`./mvn test` - Linux
`./mvnw test` - Windows


## Contributors
[JoergenKoeldal](https://github.com/JoergenKoeldal)

[PVGlimoe](https://github.com/PVGlimoe)

[MatiasStorm](https://github.com/MatiasStorm)
