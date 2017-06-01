Installation 
==============================
1. Extract the project ;
1. To generate the API JAR file, go to the root project folder and execute command : mvn clean install
2. To launch the API, execute command : java -jar target/bank-account-0.0.1-SNAPSHOT.jar

Development
==============================
Implementation of Unit Test related to TDD approach, in AccountServiceTest (JUnit)
Implementation of a simple service AccountService answering the TDD.
Implementation of an API with Spring Boot and Spring MVC which provides services in goal to answer the Bank Account Kata's User Story (testable via SoapUI)
- GET http://localhost:8080/api/accounts 
- GET http://localhost:8080/api/account/{accountNumber}/operations
- PUT http://localhost:8080/api/account/{accountNumber}/deposit (parameter : amount)
- PUT http://localhost:8080/api/account/{accountNumber}/withdrawl (parameter : amount)