#Friend Management

###TechStack
    - Spring Boot 2
    - Spring Data as ORM
    - Postgres as Database
    -Swagger for Documentation

###To Run it locally
Configure application.properties to use Postgres URL, Username and password

Change spring.jpa.hibernate.ddl-auto=none in application properties to spring.jpa.hibernate.ddl-auto=create
The above will create the tables when application starts

#DEMO
Working version can be found from 
[DEMO](https://friend-management-sp.herokuapp.com/swagger-ui.html)
The Swagger-UI can be used to see all the REST endpoints and send the request

#ERROR Json Response
All Json Response are of the form
```
{
	"success":false,
	"errorCode": <Some Code>,
	"errorMessage":"Somer Error Message"
}
```

The Error Response are defined by `ErrorEnums.java`