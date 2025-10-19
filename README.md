# BookIt  

A modern Spring Boot-based event management and ticketing system with OAuth2 authentication via Keycloak, QR code generation for tickets, and comprehensive event organization features.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Key Features Explained](#key-features-explained) 

## Features

### Event Management
- Create, read, update, and delete events
- Organize events with detailed information (name, venue, dates, sales periods)
- Multiple ticket types per event with flexible pricing
- Event status management (DRAFT, PUBLISHED, CANCELLED, COMPLETED)
- Search and filter published events
- Event organizer management with role-based access

### Ticketing System
- Purchase tickets with automatic inventory management
- Support for multiple ticket types with different prices
- Ticket status tracking (PURCHASED, CANCELLED)
- View purchased tickets with event details
- Prevent overselling with pessimistic locking on ticket types

### QR Code Validation
- Automatic QR code generation for each purchased ticket
- Two validation methods:
  - QR code scanning validation
  - Manual ticket validation
- Track validation history and status
- Ticket validation status management (VALID, INVALID, EXPIRED)

### Authentication & Security
- OAuth2 authentication with Keycloak integration
- JWT-based authorization
- Role-based access control (ORGANIZER, STAFF, ATTENDEE)
- Automatic user provisioning on first login
- Secure endpoint protection

### Error Handling
- Comprehensive exception handling
- Validation of all inputs
- Meaningful error messages
- Global exception handler for consistent error responses

## Tech Stack

### Backend
- **Framework:** Spring Boot 3.5.4
- **Language:** Java 21
- **Database:** PostgreSQL (latest)
- **Authentication:** Keycloak 23.0 (OAuth2)
- **Data Mapping:** MapStruct 1.6.3
- **Utilities:** Lombok 1.18.36
- **QR Code Generation:** ZXing 3.5.1

### Infrastructure
- **Containerization:** Docker & Docker Compose
- **Database Admin:** Adminer
- **Authentication Server:** Keycloak

### Build Tools
- **Build System:** Maven
- **JDK:** Java 21+

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java:** JDK 21 or higher
- **Maven:** 3.8.0 or higher
- **Docker:** Latest version
- **Docker Compose:** 2.0 or higher
- **Git:** For cloning the repository

## Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/salmahazem1/BookIt.git
cd BookIt
```

### Step 2: Start Infrastructure with Docker Compose

```bash
docker-compose up -d
```

This will start:
- **PostgreSQL:** Database on `localhost:5432`
- **Adminer:** Database UI on `localhost:8888`
- **Keycloak:** Authentication server on `localhost:9090`

### Step 3: Configure Keycloak

1. Open Keycloak admin console: `http://localhost:9090`
2. Login with:
   - **Username:** admin
   - **Password:** admin
3. Create a new realm called `event-ticket-platform`
4. Create a client with:
   - Client ID: `event-ticket-platform`
   - Access Type: Bearer only
   - Configure redirect URIs as needed

### Step 4: Build the Application

```bash
mvn clean install
```

## Configuration

### Application Properties

Edit `src/main/resources/application.properties`:

```properties
spring.application.name=event-ticket-platform

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=changemeinprod!

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# OAuth2 / Keycloak
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:9090/realms/event-ticket-platform

# Logging
logging.level.org.springframework.web=DEBUG
```

## Running the Application

### Option 1: Using Maven

```bash
mvn spring-boot:run
```

### Option 2: Run Compiled JAR

```bash
mvn clean package
java -jar target/tickets-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## API Documentation

### Events Endpoints

#### Create Event (Organizer only)
```http
POST /api/v1/events
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Tech Conference 2024",
  "start": "2024-12-01T09:00:00",
  "end": "2024-12-01T18:00:00",
  "venue": "Convention Center",
  "salesStart": "2024-11-01T00:00:00",
  "salesEnd": "2024-11-30T23:59:59",
  "status": "PUBLISHED",
  "ticketTypes": [
    {
      "name": "General Admission",
      "price": 50.0,
      "description": "Standard admission",
      "totalAvailable": 500
    }
  ]
}
```

#### List Events
```http
GET /api/v1/events?page=0&size=20
Authorization: Bearer <token>
```

#### Get Event Details
```http
GET /api/v1/events/{eventId}
Authorization: Bearer <token>
```

#### Update Event
```http
PUT /api/v1/events/{eventId}
Authorization: Bearer <token>
Content-Type: application/json
```

#### Delete Event
```http
DELETE /api/v1/events/{eventId}
Authorization: Bearer <token>
```

#### List Published Events (Public)
```http
GET /api/v1/events/published?page=0&size=20&q=search+term
```

#### Get Published Event Details (Public)
```http
GET /api/v1/events/published/{eventId}
```

### Tickets Endpoints

#### List User Tickets
```http
GET /api/v1/tickets?page=0&size=20
Authorization: Bearer <token>
```

#### Get Ticket Details
```http
GET /api/v1/tickets/{ticketId}
Authorization: Bearer <token>
```

#### Get Ticket QR Code
```http
GET /api/v1/tickets/{ticketId}/qr-codes
Authorization: Bearer <token>
```

### Ticket Types Endpoints

#### Purchase Ticket
```http
POST /api/v1/ticket-types/{ticketTypeId}/purchase
Authorization: Bearer <token>
```

### Ticket Validation Endpoints

#### Validate Ticket
```http
POST /api/v1/ticket-validations
Authorization: Bearer <token>
Content-Type: application/json

{
  "id": "{ticketId or qrCodeId}",
  "method": "MANUAL" or "QR_SCAN"
}
```

## Project Structure

```
event-ticket-platform/
├── src/
│   ├── main/
│   │   ├── java/com/example/tickets/
│   │   │   ├── AnEventTicketPlatformApplication.java
│   │   │   ├── config/
│   │   │   │   ├── JpaConfiguration.java
│   │   │   │   ├── JwtAuthenticationConverter.java
│   │   │   │   ├── QrCodeConfig.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controllers/
│   │   │   │   ├── EventController.java
│   │   │   │   ├── TicketController.java
│   │   │   │   ├── TicketTypeController.java
│   │   │   │   ├── TicketValidationController.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── domain/
│   │   │   │   ├── entities/
│   │   │   │   ├── dtos/
│   │   │   │   ├── enums/
│   │   │   │   └── requests/
│   │   │   ├── mappers/
│   │   │   ├── repositories/
│   │   │   ├── services/
│   │   │   ├── exceptions/
│   │   │   └── filters/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── META-INF/orm.xml
│   └── test/
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Key Features Explained

### Role-Based Access Control
- **ORGANIZER:** Can create, update, and manage events; view ticket sales
- **STAFF:** Can validate tickets at events
- **ATTENDEE:** Can purchase and view tickets

### Pessimistic Locking
Implemented on ticket type purchases to prevent overselling in high-concurrency scenarios using `@Lock(LockModeType.PESSIMISTIC_WRITE)`

### QR Code Generation
Automatic QR codes are generated for each ticket using Google ZXing library and stored as Base64-encoded PNG images

### Event Status Workflow
```
DRAFT → PUBLISHED → COMPLETED
  ↓
CANCELLED (from any state)
```
 

