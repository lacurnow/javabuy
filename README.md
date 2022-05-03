# JAVABUY

This is a java/springboot project being completed as the final project for the Maker's Academy Apprenticeship. It is an e-commerce platform.

The application uses:
  - `maven` to build the project
  - `thymeleaf` for templating
  - `flyway` to manage `postgres` db migrations
  - `selenium` for feature testing
  - `faker` to generate fake names for testing
  - `junit4` for unit testing
  - `spring-security` for authentication and user management
  

## QuickStart Instructions

- Fork and clone this repository to your machine
- Open the codebase in an IDE like InteliJ or VSCode
- Create a new Postgres database called `javabuy`
- Install Maven `brew install maven`
- Build the app and start the server, using the Maven command `mvn spring-boot:run`
> The database migrations will run automatically at this point
- Visit `http://localhost:8080/users/new` to sign up

## Running the tests

- Make sure chromedriver is installed
- You might also need geckodriver
- Start the server in a terminal session `mvn spring-boot:run`
- Start a new terminal session, navigate to the Acebook directory and then do `mvn test` to run both feature tests and unit tests

