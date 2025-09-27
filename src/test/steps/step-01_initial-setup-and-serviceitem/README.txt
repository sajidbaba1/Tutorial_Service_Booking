Step 01 — Initial Setup + Health Check + ServiceItem (Create & List)

Quick Goal
- Set up Spring Boot 3 + MySQL
- Health endpoint: GET /api/health
- Feature: ServiceItem with POST create and GET list

Pre-req
- Java 17, MySQL running, DB: service_booking_system
- Postman ready

1) Check dependencies (pom.xml)
- web, data-jpa, mysql-connector-j, validation, lombok (optional), devtools (optional)

2) Configure application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/service_booking_system
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true
spring.devtools.livereload.enabled=true

If DB missing:
CREATE DATABASE service_booking_system;

3) Health endpoint
GET http://localhost:8080/api/health -> 200 OK body: "OK"

4) ServiceItem
Entity: fields id, name (req, 100), description (500), price (req BigDecimal), active=true, createdAt=now
DTO in: CreateServiceItemRequest with @Valid (name, price)
DTO out: ServiceItemResponse
Repo: JpaRepository<ServiceItem,Long>
Service: create(), list()
Controller: POST /api/service-items, GET /api/service-items
GlobalValidation: returns 400 with field errors

5) Run
./mvnw spring-boot:run

6) Test in Postman
- Health: GET /api/health -> OK
- Create:
  POST /api/service-items
  Content-Type: application/json
  {
    "name": "AC Repair",
    "description": "Split AC general service and gas top-up",
    "price": 1499.50
  }
  Expect: 201 Created + JSON body
- List: GET /api/service-items -> array with created item
- Validation demo: POST {"name":"","price":-10} -> 400 with errors

Commit message
feat(step-01): initial setup + health + ServiceItem create/list with validation
