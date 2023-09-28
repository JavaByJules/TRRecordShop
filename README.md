# TR Record Shop

## Documentation
The documentation of TR Record Shop includes:

- [Overview](#overview)
- [Planning](#planning)
- [Testing](#testing)
- [Contribution Guide](Documentation/CONTRIBUTING.md)

<br />

## Installation
See the [Contribution Guide](Documentation/CONTRIBUTING.md)

## Running the Application

Maven build


### EndPoints


[Welcome](http://localhost:8080/api/v1/)

[GetAllAlbums](http://localhost:8080/api/v1/albums/)

[GetAlbumBy](http://localhost:8080/api/v1/album) ?title || artist || releaseYear || genre  

[PostAlbum](http://localhost:8080/api/v1/album/) Requires [Album JSON](#album-json) body

[PatchAlbum](http://localhost:8080/api/v1/album/update/) Requires [Album JSON](#album-json) body 

[PatchStockLevel](http://localhost:8080/api/v1/album/update/stock/) ?id & amount & imcrement

[DeleteAlbum](http://localhost:8080/api/v1/album/delete/) ?id


### Album Json
`{
"artist" :   "artist name",
"title" : "album title",  
"releaseYear" : "2005",  
"genre" : "POP",
"quantity" : "10"
}`


### Genres
    DANCE,
    POP,
    ROCK,
    METAL,
    ELECTRONIC,
    AFROBEATS,
    BLUES,
    HIPHOP,
    RNB,
    CLASSICAL,
    OTHER

<br />

## Overview
[Project Board](https://trello.com/b/kpMuDXdZ/record-shop-backend)

The Record Shop backend is a program designed to allow the shop to store, update and query stock data, which is held an in-memory database.
This is accomplished by creating a Spring Boot application to design an API with specific mapping for viewing and performing operations upon the shop's inventory in various ways.
Users wil interact with the API using a command-line interface or an API platform such as [Postman](https://www.postman.com/), [Swagger](https://swagger.io/) or [curl](https://curl.se/).

<br />



## Planning

### Assumptions
    • The stock level is populated when a new album is added to the database but can be updated separately afterwards
    • The list all albums by artist and get album by album name should support partial string matches and be case insensitive

### Approach
    • Apply a TDD
    • Design using OOP principals
    • Create the User stories and the UML document
    • Create the MVP, and if time add enhancements to the project
    • Create a shared GitHub repository that all can work from
    • Use branches for adding new features
    • Use a Trello board for listing and assigning tasks and  keeping track of progress
    • Prepare sample dataset that can be populated into the database
    • Use the H2 in-memory database and the H2-console to view and populate the contents of the Database
    • If time, then consider extending to use a PostGres database

### User Journey
...
![User Journey](Documentation/Resources/getAllAlbums.png)

![User Journey](Documentation/Resources/getAlbumsByArtist.png)

![User Journey](Documentation/Resources/getAlbumByAlbumName.png)

![User Journey](Documentation/Resources/insertAlbum.png)

![User Journey](Documentation/Resources/updateAlbumById.png)

![User Journey](Documentation/Resources/updateStock.png)

<br />

### Class Diagram
...

![Class Diagram](Documentation/Resources/Record-Shop-UML.jpg)

<br />

## Testing
...

## TODO  
    • Implement the help endpoint (Spring Boot Actuator)
    • Add validation checks – e.g cannot reduce quantity to < 0, check for null returned in place of Album in case it can’t be found in database, check for duplicates? etc
    • Add exception processing and handling
    • Return appropriate status codes on errors
    • Extend the test cases to include edge cases
    