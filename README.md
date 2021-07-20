# Overview

This part contains information and comments that I noted during development.

This project aims to provide a REST based application which can book order for a photo shooting service. 
Application is configured over and developed with Spring Boot Framework and Java(1.8).

This is a 5 man/hour development and 2 man/hour documentation. I tried my best probably lots of points that could be improved.

I tried to show a simple CQRS pattern with Query and Command services. Also tried to make loose coupled services that none of them can access their Data with theirs DAO objects. Because in future maybe Customer services will be another microservice. And if we use CustomerRepository anywhere that we need, it will 
cost us that time when we want to replace it. 

Some of the services maybe not working.

Spring Boot gives me the opportunity to create a REST applications. Also I used plugins to create Docker images.

With startup a sample Photographer data is uploaded. It can be listed with Photographer-API .

The uniqueness of the Order request is not handled but it should be checked.

Auditing is not done but it should be done. In my experience, we made it by ourself to keep track of what is going on for a specific entity. It can be done by custom solution or with third party libraries. Audit gives us the opportunity to investigate for the specific data.

Notification mechanism is not done. With notification I mean events. When I changed the status of the Order from PENDING to CANCEL, nobody will be notified.
Everybody should check the Order status every time before doing something. Events can give an advantage to asynchronous actions.

Entity names should be revised because using different keywords/terms can make misunderstandings within the team. Everybody must understand same thing with what PhotoOrder entity aims.Common language is important to understand each other clearly.

Maybe I should placed every request as PhotoOrderRequest as a draft request which should be clarified and verified by operators. 
And after confirmations are done PhotoOrder could be created.

Validations should be checked again.And it takes us to test coverage. More unit tests is needed in here. 

For naming photographer base62 encoding can be used. Like readable [shortlinks](https://stackoverflow.com/questions/9543715/generating-human-readable-usable-short-but-unique-ids)

No caching mechanism is used.

No lock mechanism is used. If we decide to test with clustered environments, lock mechanism should be considered.

No security nor authorization is not used. Everybody can use every APIs. Restricting the people from accessing unauthorized APIs should be considered.

Performance is ignored. With load test at least required indexs should be identified. Or caching solutions should be considered.	

For monitoring purpose Spring Actuator is used. It will help us to monitor API service performance. 

It is ready to run as a Jenkins job with 2 step pipeline.

I thought to use State Machine framework of the Spring which applies for transitions between states of the Orders. It is a nice example of State Pattern. But I need more time for it. Below I tried to show the States but I couldnt pointed the actions between the states for transition.

	PENDING >--------------------- ASSIGNED
	|      \                  /       |
	|       \                /        |
	|		     CANCEL   -----     UPLOADED
	|          /         \            |
	UNSCHEDULED           \	        |
						       \      COMPLETED

## Entity Relationship

* PhotoOrder : The main entity of the application. Orders are kept in this entity. 
Confusing with ORDER keyword this name is used. Alternatives should be discussed.
For making every Order request unique, there should be a criteria like;with combination of Location,Date/Time and Type of shooting columns. 

* Customer : Every photo shooting order request will create a unique Customer. If a customer
will send several requests it will be mapped to same Customer entity based on the email address.
Email address is selected for uniqueness of the Customer. PhotoOrder and Customer has 1-to-N relationship.

* Photographer: Every order is assigned to Photographer(s). Photogra

* PhotoAlbum : Every PhotoOrder has 1 or more PhotoAlbum created for the request. 
But only one of them will be accepted. Because of it, we have status for PhotoAlbum.
PhotoAlbum is created to upload Photos for the Order.

* Photo : Every uploaded Photo will be saved at Photo entity with created a mandatory PhotoAlbum.

	Order >-------- Customer
	  |
	  |
	  ^
	PhotoAlbum ------Photographer  
	  |
	  |
	 Photo

## API List

API s can be accessed with [Swagger UI adress](/swagger-ui/index.html) 

**Order API** : Provides solutions for Photo Order requests.
 * /api/order/create (PUT): Allows to create a new Order.
 * /api/order/schedule/{id} (POST): Schedule the time for the Given Order
 * /api/order/cancel/{id} (POST): Cancel the Order with given id
 * /api/order/assign/{id} (POST): Assign a Photographer to the Order with the given Id.
 * /api/order/list (GET): List all the Order request via Pagination
 * /api/order/{id} (GET): Get the details of the requested Order.

**Photo Album API** : This API provides resources for Photo Album Data
 * /api/photo-album/create (PUT): Create a Photo Album for an Order to upload Photos
 * /api/photo-album/complete (POST): Upload photos to Photo Album is finished.
 * ​/api​/photo-album​/list (GET): List the photo albums with pagination

**Photo API** : This API provides resources for Photo Album Data
 * /api/photo/upload/{photoAlbumId} (PUT): Upload Photo to the specific Photo Album
 * /api/photo/list/{photoAlbumId} (GET): List the photos of the given album.

**Photographer API** : This API provides resources for Photo Album Data
 * /api/photographer/list (GET): List all the Photographers via pagination.


# Information

* Database for User Accounts will be stored in memory H2 test_database.

* API Documentation is available [with Docker](http://localhost:8080/swagger-ui/index.html) and [without Docker](http://localhost:8081/swagger-ui/index.html) 

* Application Health can be test at [with Docker](http://localhost:8080/actuator/health) and [without Docker](http://localhost:8081/actuator/health) 


## Configuration
* Java 8
* Spring Boot 2.3.4.RELEASE
* Spring Data JPA
* Spring Actuator
* Palantir Docker 0.26.0
* H2 In Memory Database
* Lombok 
* ModelMapper
* Gradle 

## Building Application (Windows)

* Testing

`gradlew.bat test`

* Building (no tests)

`gradlew.bat assemble`

* Building (with tests)

`gradlew.bat build`

* Running in Docker

`gradlew.bat assemble docker dockerRun`

* Stopping Docker container

`gradlew.bat dockerStop`





### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/gradle-plugin/reference/html/#build-image)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#production-ready)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#using-boot-devtools)
* [Validation](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-validation)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans â€“ insights for your project's build](https://scans.gradle.com#gradle)

