Title: Step 01 — Initial Setup + Health Check + ServiceItem (Create & List)

Goal
- Initialize project with Spring Boot 3, connect to MySQL, add validation.
- Add a health endpoint: GET /api/health.
- Implement first feature: ServiceItem with POST (create) and GET (list) endpoints.
- Verify everything using Postman.

Pre-requisites
- Java 17 installed
- Maven (wrapper is included: mvnw/mvnw.cmd)
- MySQL running locally, with a database created: service_booking_system
- IDE (IntelliJ, VS Code) and Postman installed

1) Verify pom.xml dependencies
File: pom.xml
Ensure these dependencies exist (already added):
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- mysql-connector-j
- spring-boot-starter-validation
- lombok (optional, we use explicit getters/setters for clarity)
- spring-boot-devtools (optional for live reload)

2) Configure application.properties
File: src/main/resources/application.properties
Values set (adjust username/password if needed):
- spring.application.name=ServiceBookingSystem
- spring.datasource.url=jdbc:mysql://localhost:3306/service_booking_system
- spring.datasource.username=root
- spring.datasource.password=root
- spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.show-sql=true
- spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
- spring.jpa.properties.hibernate.format_sql=true
- spring.devtools.livereload.enabled=true

Create the DB if missing (MySQL):
CREATE DATABASE service_booking_system;

3) Project entry point
File: src/main/java/com/sajid/ServiceBookingSystem/ServiceBookingSystemApplication.java
- @SpringBootApplication main class. No changes required.

4) Health endpoint
File: src/main/java/com/sajid/ServiceBookingSystem/controller/HealthController.java
- @RestController
- @RequestMapping("/api/health")
- GET returns 200 OK with body "OK"

5) ServiceItem feature (Create + List)
Entity
File: src/main/java/com/sajid/ServiceBookingSystem/entity/ServiceItem.java
- @Entity, @Table(name = "service_items")
- Fields: id (IDENTITY), name (required, 100), description (500), price (BigDecimal, required), active (default true), createdAt (now)

DTOs
File: src/main/java/com/sajid/ServiceBookingSystem/dto/CreateServiceItemRequest.java
- Validations: @NotBlank name, @Size limits, @NotNull/@DecimalMin/@Digits price

File: src/main/java/com/sajid/ServiceBookingSystem/dto/ServiceItemResponse.java
- Response shape: id, name, description, price, active, createdAt

Repository
File: src/main/java/com/sajid/ServiceBookingSystem/repository/ServiceItemRepository.java
- extends JpaRepository<ServiceItem, Long>

Service
File: src/main/java/com/sajid/ServiceBookingSystem/service/ServiceItemService.java
- Methods: create(), list()

File: src/main/java/com/sajid/ServiceBookingSystem/service/impl/ServiceItemServiceImpl.java
- Implements create() and list(); maps entity -> response via toResponse()

Controller
File: src/main/java/com/sajid/ServiceBookingSystem/controller/ServiceItemController.java
- @RequestMapping("/api/service-items")
- POST /api/service-items with @Valid CreateServiceItemRequest
- GET /api/service-items returns List<ServiceItemResponse>

Global validation errors
File: src/main/java/com/sajid/ServiceBookingSystem/exception/GlobalExceptionHandler.java
- Returns 400 with field errors for validation failures

6) Run the application
From project root:
Windows PowerShell:
./mvnw spring-boot:run

7) Test in Postman
Health Check
- GET http://localhost:8080/api/health
- Expect: 200 OK, body "OK"

Create ServiceItem
- POST http://localhost:8080/api/service-items
- Headers: Content-Type: application/json
- Body:
{
  "name": "AC Repair",
  "description": "Split AC general service and gas top-up",
  "price": 1499.50
}
- Expect: 201 Created, Location header "/api/service-items/{id}", JSON body with id, name, description, price, active, createdAt

List ServiceItems
- GET http://localhost:8080/api/service-items
- Expect: 200 OK, array containing the created item

Validation demo (optional for video)
- POST with body: { "name": "", "price": -10 }
- Expect: 400 Bad Request with error messages for fields

8) Suggested commit message
feat(step-01): initial setup + health endpoint + ServiceItem create/list with validation

9) Notes for consistency
- Endpoints are stable; we won’t change earlier APIs later.
- Hibernate dialect uses org.hibernate.dialect.MySQLDialect (Spring Boot 3 compatible).
- jakarta.* imports are used by default via Spring Boot 3.
