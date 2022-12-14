# web_app_for_hk_holidays

Web applications using `Java` `Spring` and `MySQL`

<!-- a.	How to compile, start and test the application -->
## Instruction


### Completed Features

Web applications for listing and searching HK holidays from 2021 to 2023

- store the HK holidays in database
- output the holiday list from database
- filter & re-output the holiday list from database


### Setup

- Run MySQL database in localhost
- Create a `hk_holidays` batabase
- Setup the user and password in  

### Compile & Start

1. In Linux or Terminal environment, complie & run this project in the depository where the `mvnw.cmd` located, using Unix shell script of maven.

```
PS D:\holidays> ../mvnw package
PS D:\holidays> ../mvnw spring-boot:run

```
2. Open a web browser with url `http://localhost:8080/`

### Test

Utilised framework JUnit to perform the unit testing for parsing Json and implementing database.



<!-- b.	A short diary, let us know how you learn / think / try / develop for each day -->


## Work Diary

### 12 Dec (1st Day)



- Planned for the project: requested element, app architetecure, framework to use, check-points

- Setup architecture

- Create a new `Spring-boot` project for web app

- Built a class `HomeController` Utilising `springframework` to construct web bind
    
    - a method `get` for getting data from Json

    - a method `get` for getting data from database

- Design basic GUI with `HTML`

- build a `JsonHandler` class to parse Json file

    - setup `org.json` dependency in `pom.xml`

    - JUnit testing

- Built a class `Holday` for `ui.Model`



### 13 Dec (2nd Day)

- Implement database `MySQL` with `java.sql`

    - Setup MySQL driver

    - Setup methods data manipulation 

        - Create a new table in DB

        - Update/extract data from DB

    - JUnit testing

- Optimise

### 14 Dec (3rd Day)

- Optimise

- Documentation



| column 1 | column 2 |
|------------|----------|
| Planned for the project | <ul><li>value 1</li><li>value 2</li></ul> |
| value | <ul><li>requested element</li><li>value 2</li></ul> |
| value | <ul><li>value 1</li><li>value 2</li></ul> |
| value | <ul><li>value 1</li><li>value 2</li></ul> |
| value | <ul><li>value 1</li><li>value 2</li></ul> |
