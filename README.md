# web_app_for_hk_holidays

Web applications using `Java` `Spring` and `MySQL`

<!-- a.	How to compile, start and test the application -->
## Instruction


### Completed Features

Web applications for searching HK holidays from 2021 to 2023

- store the HK holidays in database
- output the holiday list from database
- filter & re-output the holiday list from database


### Setup

- Run MySQL database in localhost
- Create a `hk_holidays` batabase
- Setup the user and password in  

### Compile & Strat

Complie in the depositry

```
PS D:\holidays> ../mvnw spring-boot:run

```

### 


### Test

Utilised framework JUnit to perform the unit testing for parsing Json and implementing database.



<!-- b.	A short diary, let us know how you learn / think / try / develop for each day -->


## Work Diary

12 Dec (1st Day)

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



13 Dec (2nd Day)

- Implement database `MySQL` with `java.sql`

    - Setup MySQL driver

    - Setup methods data manipulation 

        - Create a new table in DB

        - Update/extract data from DB

    - JUnit testing

- Optimise

14 Dec (3rd Day)

- Optimise

- Documentation


| Categroy | Tasks    | Notes    |
| :---:   | :---: | :---: |
| Seconds | 301   | 283   |
