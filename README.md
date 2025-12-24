### AgroX – Agroindustrial Management API

Overview
AgroX is a RESTful API developed in Java using Spring Boot, focused on managing operations in an agroindustrial context.
The system covers the management of categories, products, inventory, employees, and service orders, with strong emphasis on domain business rules, state consistency, and good design practices.

The project has a didactic and evolutionary nature and is used to consolidate advanced backend concepts with Java and Spring.

### Project Objectives
- Consolidate the use of Spring Boot and Spring Data JPA
- Apply a Domain-Driven Design (DDD – light) approach
- Practice clear separation of responsibilities
- Centralize business rules within the domain
- Use DTOs as the API contract
- Implement global exception handling
- Develop unit tests focused on business rules

### Application Architecture
The application follows a layered architecture with a well-defined flow:

Controller → Service → Domain → Repository

### Package Structure  
afsj.agrox  
```
├── controllers   → REST layer (HTTP)  
├── services      → Use cases and orchestration  
├── entities      → Rich domain model  
├── repositories  → Persistence (Spring Data JPA)  
├── dtos          → Input and output DTOs  
├── mapper        → DTO ↔ Entity mapping  
├── exceptions    → Exceptions and global handler  
├── validations   → Domain validations  
├── enums         → Domain enumerations
```  

### Technical Concepts and Decisions

Rich Domain Model
Entities encapsulate both state and behavior, avoiding an anemic model.
Product directly manages its inventory.
ServiceOrder controls its lifecycle and items.
Employee handles hiring, updates, and dismissal.

Value Object – Stock
Product inventory is modeled as an embedded Value Object (Embeddable).
Responsibilities:
- Validate quantities
- Control increases and decreases
- Detect low stock
- Enforce domain invariants

Business Rules in the Domain
Validations use the DomainValidation utility, throwing DomainException when a rule is violated.
Examples:
- Inventory cannot become negative
- Finalized service orders cannot be modified
- Employees cannot be dismissed twice
- Invalid dates are rejected at the domain level

DTOs and Mappers  
The API never exposes entities directly.
DTOs represent the API contract.
Mappers perform explicit conversions.
This avoids leaking internal details and improves maintainability.

State Control – Service Orders  
Possible states:
- PENDING
- COMPLETED
- CANCELLED

Rules:
- Only PENDING orders can be modified
- When finalized, product inventory is reduced
- Cancelled or completed orders become immutable

### API Endpoints

Categories
```
GET    /categories  
GET    /categories/{id}  
POST   /categories  
DELETE /categories/{id}
```

Products
```
GET    /products  
GET    /products/{id}  
POST   /products  
PATCH  /products/{id}  
PATCH  /products/{id}/stock/increase  
PATCH  /products/{id}/stock/decrease  
DELETE /products/{id}  
```

Employees
```
GET    /employees  
GET    /employees/{id}  
POST   /employees  
PATCH  /employees/{id}  
DELETE /employees/{id}
```  

Service Orders  
```
GET    /orders  
GET    /orders/{id}  
POST   /orders  
PATCH  /orders/{orderId}/items/{productId}/increase  
PATCH  /orders/{orderId}/items/{productId}/decrease  
PATCH  /orders/{orderId}/finish  
PATCH  /orders/{orderId}/cancel  
DELETE /orders/{id}
```  

### Error Handling  
Exception handling is centralized using RestControllerAdvice.

Standard error format:  
```json
{  
"timestamp": "...",  
"status": 400,  
"error": "Bad Request",  
"message": "Error description",  
"path": "/endpoint"  
}  
```
Handled cases:
- Resource not found (404)
- Business rule violation (422)
- Data conflict (409)
- Validation errors (400)
- Internal errors (500)

### Tests
The project includes unit tests focused on the domain layer.
Implemented tests:
- CategoryTests
- ProductTests
- EmployeeTests
- ServiceOrderTests

### Environment Profiles  
dev     → In-memory H2 database  
docker  → MySQL  

### Running with Docker  
>docker-compose up --build  

### Running Locally
Prerequisites:
- Java 17+
- Maven

Run:
>./mvnw spring-boot:run

### Access:  
>API: http://localhost:8080  
H2 Console: http://localhost:8080/h2-console  

### Final Considerations  
AgroX was developed as a technical consolidation project, prioritizing architectural clarity, best practices, and an expressive domain model.
It is ideal for advanced Spring Boot studies, incremental architectural evolution, and demonstrating backend maturity with Java.
