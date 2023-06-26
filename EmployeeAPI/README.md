# EmployeeAPI

## Overview

-----

The EmployeeAPI was built using Spring Boot 3.1.0 and Java 17.

Users of this API should be able to:
- Add a new employee
- Update an employee’s detail.
- Remove an employee
- List Department structure. (ie. All employees in a hierarchy)
- Calculate the cost allocation of a Department.
- Calculate the cost allocation of a Manager.

**Method  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Endpoint &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Uses**

--------
GET  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;/empapi/employees&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Find all employees\
POST  &emsp;&emsp;&emsp;&emsp;&emsp;/empapi/employees&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Add a new employee\
PUT  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;/empapi/employees/{id}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Update an employee detail\
DELETE &emsp;&emsp;&emsp;&emsp;/empapi/employees/{id}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Remove an employee\
GET  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;/empapi/department/{department}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;List Department Structure\
GET  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;/costapi/department/{department}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Calculate cost allocation of a Department\
GET  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;/costapi/manager/{id}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Calculate cost allocation of a Manager




                    
## Prerequisites:

- Java 17

## Database

This application uses an H2 in-memory database.

The following URL can be used to login and access the console.

>http://localhost:8081/h2-console
 
- JDBC URL: jdbc:h2:mem:employee_db
- Username: sa
- Password: emp123

## Postman

Postman collection is attached to the source code in the postman folder:

> EmployeeAPI.postman_collection.json

## Download and Run

Jar location: employee-service/EmployeeAPI/artifacts/EmployeeAPI_jar.jar
> java -jar EmployeeAPI_jar.jar
