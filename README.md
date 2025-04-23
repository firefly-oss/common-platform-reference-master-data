# Common Platform Reference Master Data

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Data Model](#data-model)
- [API Reference](#api-reference)
  - [Countries API](#countries-api)
  - [Currencies API](#currencies-api)
  - [Administrative Divisions API](#administrative-divisions-api)
  - [Branches API](#branches-api)
  - [Bank Institution Codes API](#bank-institution-codes-api)
  - [Holidays API](#holidays-api)
  - [Language Locales API](#language-locales-api)
  - [Lookup Domains and Items API](#lookup-domains-and-items-api)
  - [Legal Forms API](#legal-forms-api)
  - [Activity Codes API](#activity-codes-api)
- [Setup and Installation](#setup-and-installation)
  - [Prerequisites](#prerequisites)
  - [Building the Application](#building-the-application)
  - [Running Locally](#running-locally)
  - [Environment Variables](#environment-variables)
- [Deployment](#deployment)
  - [Docker](#docker)
  - [Kubernetes](#kubernetes)
- [Development Guidelines](#development-guidelines)
  - [Code Style](#code-style)
  - [Testing](#testing)
  - [Database Migrations](#database-migrations)
- [Contributing](#contributing)

## Overview
The Common Platform Reference Master Data microservice is a core component of the Firefly platform, responsible for managing and providing access to various types of reference and master data used across the platform. This microservice provides a centralized repository for commonly used data such as countries, currencies, bank codes, and other reference information.

## Features
- RESTful API for managing reference data
- Reactive programming model using Spring WebFlux
- Comprehensive data model for various types of master data
- Versioned API endpoints
- OpenAPI/Swagger documentation
- Containerized deployment
- Pagination support for large data sets
- Filtering capabilities for efficient data retrieval

## Architecture
The microservice follows a modular architecture with the following components:

### Modules
- **common-platform-reference-master-data-models**: Contains the database entities and repositories
- **common-platform-reference-master-data-interfaces**: Contains DTOs and service interfaces
- **common-platform-reference-master-data-core**: Contains service implementations and business logic
- **common-platform-reference-master-data-web**: Contains REST controllers and web configuration

### Technologies
- **Spring Boot**: Application framework
- **Spring WebFlux**: Reactive web framework
- **R2DBC**: Reactive database connectivity
- **Flyway**: Database migration
- **OpenAPI/Swagger**: API documentation
- **Docker**: Containerization

## Data Model
The microservice manages the following types of reference data:

1. **Countries**: Country information including ISO codes, names, and regions
2. **Currencies**: Currency information including ISO codes, symbols, and decimal precision
3. **Administrative Divisions**: Hierarchical administrative divisions within countries (states, provinces, etc.)
4. **Branches**: Branch information for financial institutions
5. **Bank Institution Codes**: Banking codes and identifiers
6. **Holidays**: Holiday information for different countries and regions
7. **Language Locales**: Language and locale information
8. **Lookup Domains and Items**: Generic lookup values for reference data
9. **Legal Forms**: Legal entity types and forms
10. **Activity Codes**: Business activity classification codes

## API Reference
All API endpoints follow a versioned structure (e.g., `/api/v1/...`). The API supports standard HTTP methods (GET, POST, PUT, DELETE) for CRUD operations.

### Common Request/Response Patterns

#### Pagination
List endpoints support pagination with the following query parameters:
- `page`: Page number (zero-based)
- `size`: Number of items per page
- `sort`: Sort field and direction (e.g., `name,asc`)

Example pagination request:
```
GET /api/v1/countries?page=0&size=10&sort=countryName,asc
```

#### Response Format
All list endpoints return paginated responses in the following format:
```json
{
  "content": [
  ],
  "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalElements": 250,
  "totalPages": 25,
  "last": false,
  "first": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 10,
  "size": 10,
  "number": 0,
  "empty": false
}
```

### Countries API
Endpoints for managing country information.

#### List Countries
```
GET /api/v1/countries
```

Example response:
```json
{
  "content": [
    {
      "countryId": 1,
      "isoCode": "US",
      "countryName": "United States",
      "regionLkpId": 1,
      "status": "ACTIVE",
      "svgFlag": "data:image/svg+xml;base64,...",
      "dateCreated": "2023-01-01T00:00:00",
      "dateUpdated": "2023-01-01T00:00:00"
    },
    {
      "countryId": 2,
      "isoCode": "CA",
      "countryName": "Canada",
      "regionLkpId": 1,
      "status": "ACTIVE",
      "svgFlag": "data:image/svg+xml;base64,...",
      "dateCreated": "2023-01-01T00:00:00",
      "dateUpdated": "2023-01-01T00:00:00"
    }
  ],
  "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalElements": 250,
  "totalPages": 25,
  "last": false,
  "first": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 10,
  "size": 10,
  "number": 0,
  "empty": false
}
```

#### Get Country by ID
```
GET /api/v1/countries/{countryId}
```

Example response:
```json
{
  "countryId": 1,
  "isoCode": "US",
  "countryName": "United States",
  "regionLkpId": 1,
  "status": "ACTIVE",
  "svgFlag": "data:image/svg+xml;base64,...",
  "dateCreated": "2023-01-01T00:00:00",
  "dateUpdated": "2023-01-01T00:00:00"
}
```

#### Create Country
```
POST /api/v1/countries
```

Example request body:
```json
{
  "isoCode": "FR",
  "countryName": "France",
  "regionLkpId": 2,
  "status": "ACTIVE",
  "svgFlag": "data:image/svg+xml;base64,..."
}
```

Example response:
```json
{
  "countryId": 3,
  "isoCode": "FR",
  "countryName": "France",
  "regionLkpId": 2,
  "status": "ACTIVE",
  "svgFlag": "data:image/svg+xml;base64,...",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Update Country
```
PUT /api/v1/countries/{countryId}
```

Example request body:
```json
{
  "isoCode": "FR",
  "countryName": "French Republic",
  "regionLkpId": 2,
  "status": "ACTIVE",
  "svgFlag": "data:image/svg+xml;base64,..."
}
```

Example response:
```json
{
  "countryId": 3,
  "isoCode": "FR",
  "countryName": "French Republic",
  "regionLkpId": 2,
  "status": "ACTIVE",
  "svgFlag": "data:image/svg+xml;base64,...",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T11:45:00"
}
```

#### Delete Country
```
DELETE /api/v1/countries/{countryId}
```

Response: HTTP 204 No Content

### Currencies API
Endpoints for managing currency information.

#### List Currencies
```
GET /api/v1/currencies
```

Example response:
```json
{
  "content": [
    {
      "currencyId": 1,
      "isoCode": "USD",
      "currencyName": "US Dollar",
      "symbol": "$",
      "decimalPrecision": 2,
      "isMajor": true,
      "status": "ACTIVE",
      "dateCreated": "2023-01-01T00:00:00",
      "dateUpdated": "2023-01-01T00:00:00"
    },
    {
      "currencyId": 2,
      "isoCode": "EUR",
      "currencyName": "Euro",
      "symbol": "€",
      "decimalPrecision": 2,
      "isMajor": true,
      "status": "ACTIVE",
      "dateCreated": "2023-01-01T00:00:00",
      "dateUpdated": "2023-01-01T00:00:00"
    }
  ],
  "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalElements": 170,
  "totalPages": 17,
  "last": false,
  "first": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 10,
  "size": 10,
  "number": 0,
  "empty": false
}
```

#### Get Currency by ID
```
GET /api/v1/currencies/{currencyId}
```

Example response:
```json
{
  "currencyId": 1,
  "isoCode": "USD",
  "currencyName": "US Dollar",
  "symbol": "$",
  "decimalPrecision": 2,
  "isMajor": true,
  "status": "ACTIVE",
  "dateCreated": "2023-01-01T00:00:00",
  "dateUpdated": "2023-01-01T00:00:00"
}
```

#### Create Currency
```
POST /api/v1/currencies
```

Example request body:
```json
{
  "isoCode": "GBP",
  "currencyName": "British Pound",
  "symbol": "£",
  "decimalPrecision": 2,
  "isMajor": true,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "currencyId": 3,
  "isoCode": "GBP",
  "currencyName": "British Pound",
  "symbol": "£",
  "decimalPrecision": 2,
  "isMajor": true,
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Update Currency
```
PUT /api/v1/currencies/{currencyId}
```

Example request body:
```json
{
  "isoCode": "GBP",
  "currencyName": "Pound Sterling",
  "symbol": "£",
  "decimalPrecision": 2,
  "isMajor": true,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "currencyId": 3,
  "isoCode": "GBP",
  "currencyName": "Pound Sterling",
  "symbol": "£",
  "decimalPrecision": 2,
  "isMajor": true,
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T11:45:00"
}
```

#### Delete Currency
```
DELETE /api/v1/currencies/{currencyId}
```

Response: HTTP 204 No Content

### Legal Forms API
Endpoints for managing legal form information.

#### List Legal Forms
```
GET /api/v1/legal-forms
```

Example response:
```json
{
  "content": [
    {
      "legalFormId": 1,
      "countryId": 1,
      "code": "LLC",
      "name": "Limited Liability Company",
      "description": "A US business structure that protects its owners from personal responsibility for its debts or liabilities",
      "isCorporate": true,
      "entityType": "COMPANY",
      "status": "ACTIVE"
    },
    {
      "legalFormId": 2,
      "countryId": 1,
      "code": "CORP",
      "name": "Corporation",
      "description": "A legal entity that is separate and distinct from its owners",
      "isCorporate": true,
      "entityType": "COMPANY",
      "status": "ACTIVE"
    }
  ],
  "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalElements": 50,
  "totalPages": 5,
  "last": false,
  "first": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 10,
  "size": 10,
  "number": 0,
  "empty": false
}
```

#### Get Legal Form by ID
```
GET /api/v1/legal-forms/{legalFormId}
```

Example response:
```json
{
  "legalFormId": 1,
  "countryId": 1,
  "code": "LLC",
  "name": "Limited Liability Company",
  "description": "A US business structure that protects its owners from personal responsibility for its debts or liabilities",
  "isCorporate": true,
  "entityType": "COMPANY",
  "status": "ACTIVE"
}
```

#### Create Legal Form
```
POST /api/v1/legal-forms
```

Example request body:
```json
{
  "countryId": 3,
  "code": "SARL",
  "name": "Société à responsabilité limitée",
  "description": "French limited liability company",
  "isCorporate": true,
  "entityType": "COMPANY",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "legalFormId": 3,
  "countryId": 3,
  "code": "SARL",
  "name": "Société à responsabilité limitée",
  "description": "French limited liability company",
  "isCorporate": true,
  "entityType": "COMPANY",
  "status": "ACTIVE"
}
```

#### Update Legal Form
```
PUT /api/v1/legal-forms/{legalFormId}
```

Example request body:
```json
{
  "countryId": 3,
  "code": "SARL",
  "name": "Société à responsabilité limitée",
  "description": "French private limited liability company",
  "isCorporate": true,
  "entityType": "COMPANY",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "legalFormId": 3,
  "countryId": 3,
  "code": "SARL",
  "name": "Société à responsabilité limitée",
  "description": "French private limited liability company",
  "isCorporate": true,
  "entityType": "COMPANY",
  "status": "ACTIVE"
}
```

#### Delete Legal Form
```
DELETE /api/v1/legal-forms/{legalFormId}
```

Response: HTTP 204 No Content

### Administrative Divisions API
Endpoints for managing administrative divisions (states, provinces, etc.).

For detailed API documentation on this and other endpoints, access the Swagger UI at `/swagger-ui.html` when the application is running.

### Branches API
Endpoints for managing branch information for financial institutions.

For detailed API documentation on this and other endpoints, access the Swagger UI at `/swagger-ui.html` when the application is running.

### Bank Institution Codes API
Endpoints for managing banking codes and identifiers.

For detailed API documentation on this and other endpoints, access the Swagger UI at `/swagger-ui.html` when the application is running.

### Holidays API
Endpoints for managing holiday information for different countries and regions.

For detailed API documentation on this and other endpoints, access the Swagger UI at `/swagger-ui.html` when the application is running.

### Language Locales API
Endpoints for managing language and locale information.

For detailed API documentation on this and other endpoints, access the Swagger UI at `/swagger-ui.html` when the application is running.

### Lookup Domains and Items API
Endpoints for managing generic lookup values for reference data.

For detailed API documentation on this and other endpoints, access the Swagger UI at `/swagger-ui.html` when the application is running.

### Activity Codes API
Endpoints for managing business activity classification codes.

For detailed API documentation on this and other endpoints, access the Swagger UI at `/swagger-ui.html` when the application is running.

## Setup and Installation

### Prerequisites
- Java 21 or higher
- Maven 3.8 or higher
- PostgreSQL database

### Building the Application
```bash
mvn clean install
```

### Running Locally
```bash
mvn spring-boot:run -pl common-platform-reference-master-data-web
```

### Environment Variables
The following environment variables can be configured:

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_DATASOURCE_URL` | Database connection URL | jdbc:postgresql://localhost:5432/master_data |
| `SPRING_DATASOURCE_USERNAME` | Database username | postgres |
| `SPRING_DATASOURCE_PASSWORD` | Database password | postgres |
| `SERVER_PORT` | Application port | 8080 |

## Deployment

### Docker
The application can be deployed as a Docker container:

```bash
# Build the Docker image
docker build -t common-platform-reference-master-data .

# Run the container
docker run -p 8080:8080 common-platform-reference-master-data
```

### Kubernetes
For Kubernetes deployment, use the provided Kubernetes manifests in the deployment directory.

## Development Guidelines

### Code Style
- Follow the Google Java Style Guide
- Use reactive programming patterns with WebFlux
- Implement proper error handling and validation

### Testing
- Write unit tests for services and controllers
- Use reactive testing utilities for WebFlux components
- Ensure test coverage for critical components

### Database Migrations
- Use Flyway for database migrations
- Place migration scripts in `common-platform-reference-master-data-models/src/main/resources/db/migration`
- Follow the naming convention `V{version}__{description}.sql`

## Contributing
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Create a new Pull Request
