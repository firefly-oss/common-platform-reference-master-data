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
  - [Consent Catalog API](#consent-catalog-api)
  - [Title Master API](#title-master-api)
  - [Relationship Type Master API](#relationship-type-master-api)
  - [Notification Message Catalog API](#notification-message-catalog-api)
  - [Message Type Catalog API](#message-type-catalog-api)
  - [Document Template Catalog API](#document-template-catalog-api)
  - [Document Template Type Catalog API](#document-template-type-catalog-api)
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
11. **Consent Catalog**: Catalog of consent types that can be given by users
12. **Title Master**: Title prefixes (Mr., Mrs., Dr., etc.) used in addressing individuals
13. **Relationship Type Master**: Types of relationships between entities (Beneficiary, CEO, etc.)
14. **Notification Message Catalog**: Catalog of notification messages that can be sent to users based on events, with support for localization, template variables, and HTML templates
15. **Document Template Catalog**: Catalog of document templates that can be used for generating documents in various formats, with support for localization and template variables

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

#### List Administrative Divisions
```
GET /api/v1/divisions
```

Example response:
```json
{
  "content": [
    {
      "divisionId": 1,
      "countryId": 1,
      "code": "CA",
      "name": "California",
      "level": "STATE",
      "parentDivisionId": null,
      "status": "ACTIVE",
      "postalCodePattern": "9[0-6]\\d{3}",
      "timeZone": "America/Los_Angeles"
    },
    {
      "divisionId": 2,
      "countryId": 1,
      "code": "NY",
      "name": "New York",
      "level": "STATE",
      "parentDivisionId": null,
      "status": "ACTIVE",
      "postalCodePattern": "1[0-4]\\d{3}",
      "timeZone": "America/New_York"
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

#### Get Administrative Division by ID
```
GET /api/v1/divisions/{divisionId}
```

Example response:
```json
{
  "divisionId": 1,
  "countryId": 1,
  "code": "CA",
  "name": "California",
  "level": "STATE",
  "parentDivisionId": null,
  "status": "ACTIVE",
  "postalCodePattern": "9[0-6]\\d{3}",
  "timeZone": "America/Los_Angeles"
}
```

#### Create Administrative Division
```
POST /api/v1/divisions
```

Example request body:
```json
{
  "countryId": 1,
  "code": "OR",
  "name": "Oregon",
  "level": "STATE",
  "parentDivisionId": null,
  "status": "ACTIVE",
  "postalCodePattern": "97[0-9]{3}",
  "timeZone": "America/Los_Angeles"
}
```

Example response:
```json
{
  "divisionId": 3,
  "countryId": 1,
  "code": "OR",
  "name": "Oregon",
  "level": "STATE",
  "parentDivisionId": null,
  "status": "ACTIVE",
  "postalCodePattern": "97[0-9]{3}",
  "timeZone": "America/Los_Angeles"
}
```

#### Update Administrative Division
```
PUT /api/v1/divisions/{divisionId}
```

Example request body:
```json
{
  "countryId": 1,
  "code": "OR",
  "name": "Oregon State",
  "level": "STATE",
  "parentDivisionId": null,
  "status": "ACTIVE",
  "postalCodePattern": "97[0-9]{3}",
  "timeZone": "America/Los_Angeles"
}
```

Example response:
```json
{
  "divisionId": 3,
  "countryId": 1,
  "code": "OR",
  "name": "Oregon State",
  "level": "STATE",
  "parentDivisionId": null,
  "status": "ACTIVE",
  "postalCodePattern": "97[0-9]{3}",
  "timeZone": "America/Los_Angeles"
}
```

#### Delete Administrative Division
```
DELETE /api/v1/divisions/{divisionId}
```

Response: HTTP 204 No Content

### Branches API
Endpoints for managing branch information for financial institutions.

#### List Branches
```
GET /api/v1/branches
```

Example response:
```json
{
  "content": [
    {
      "branchId": 1,
      "branchCode": "NYC-001",
      "branchName": "Manhattan Main Branch",
      "address": "123 Wall Street",
      "postalCode": "10005",
      "city": "New York",
      "divisionId": 2,
      "countryId": 1,
      "phoneNumber": "+1-212-555-1234",
      "email": "manhattan@bank.com",
      "branchTypeLkpId": 1,
      "branchManagerId": 101,
      "openingHours": "Mon-Fri: 9:00-17:00",
      "status": "ACTIVE",
      "dateCreated": "2023-01-01T00:00:00",
      "dateUpdated": "2023-01-01T00:00:00"
    },
    {
      "branchId": 2,
      "branchCode": "SF-001",
      "branchName": "San Francisco Downtown",
      "address": "456 Market Street",
      "postalCode": "94105",
      "city": "San Francisco",
      "divisionId": 1,
      "countryId": 1,
      "phoneNumber": "+1-415-555-6789",
      "email": "sf@bank.com",
      "branchTypeLkpId": 1,
      "branchManagerId": 102,
      "openingHours": "Mon-Fri: 9:00-17:00, Sat: 10:00-14:00",
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
  "totalElements": 45,
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

#### Get Branch by ID
```
GET /api/v1/branches/{branchId}
```

Example response:
```json
{
  "branchId": 1,
  "branchCode": "NYC-001",
  "branchName": "Manhattan Main Branch",
  "address": "123 Wall Street",
  "postalCode": "10005",
  "city": "New York",
  "divisionId": 2,
  "countryId": 1,
  "phoneNumber": "+1-212-555-1234",
  "email": "manhattan@bank.com",
  "branchTypeLkpId": 1,
  "branchManagerId": 101,
  "openingHours": "Mon-Fri: 9:00-17:00",
  "status": "ACTIVE",
  "dateCreated": "2023-01-01T00:00:00",
  "dateUpdated": "2023-01-01T00:00:00"
}
```

#### Create Branch
```
POST /api/v1/branches
```

Example request body:
```json
{
  "branchCode": "CHI-001",
  "branchName": "Chicago Downtown",
  "address": "789 Michigan Avenue",
  "postalCode": "60601",
  "city": "Chicago",
  "divisionId": 4,
  "countryId": 1,
  "phoneNumber": "+1-312-555-9876",
  "email": "chicago@bank.com",
  "branchTypeLkpId": 1,
  "branchManagerId": 103,
  "openingHours": "Mon-Fri: 9:00-17:00",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "branchId": 3,
  "branchCode": "CHI-001",
  "branchName": "Chicago Downtown",
  "address": "789 Michigan Avenue",
  "postalCode": "60601",
  "city": "Chicago",
  "divisionId": 4,
  "countryId": 1,
  "phoneNumber": "+1-312-555-9876",
  "email": "chicago@bank.com",
  "branchTypeLkpId": 1,
  "branchManagerId": 103,
  "openingHours": "Mon-Fri: 9:00-17:00",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Update Branch
```
PUT /api/v1/branches/{branchId}
```

Example request body:
```json
{
  "branchCode": "CHI-001",
  "branchName": "Chicago Main Branch",
  "address": "789 Michigan Avenue",
  "postalCode": "60601",
  "city": "Chicago",
  "divisionId": 4,
  "countryId": 1,
  "phoneNumber": "+1-312-555-9876",
  "email": "chicago.main@bank.com",
  "branchTypeLkpId": 1,
  "branchManagerId": 103,
  "openingHours": "Mon-Fri: 9:00-17:00, Sat: 10:00-14:00",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "branchId": 3,
  "branchCode": "CHI-001",
  "branchName": "Chicago Main Branch",
  "address": "789 Michigan Avenue",
  "postalCode": "60601",
  "city": "Chicago",
  "divisionId": 4,
  "countryId": 1,
  "phoneNumber": "+1-312-555-9876",
  "email": "chicago.main@bank.com",
  "branchTypeLkpId": 1,
  "branchManagerId": 103,
  "openingHours": "Mon-Fri: 9:00-17:00, Sat: 10:00-14:00",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T11:45:00"
}
```

#### Delete Branch
```
DELETE /api/v1/branches/{branchId}
```

Response: HTTP 204 No Content

### Bank Institution Codes API
Endpoints for managing banking codes and identifiers.

#### List Bank Institution Codes
```
GET /api/v1/bank-institution-codes
```

Example response:
```json
{
  "content": [
    {
      "institutionId": 1,
      "bankName": "Chase Bank",
      "swiftCode": "CHASUS33",
      "routingNumber": "021000021",
      "ibanPrefix": "US",
      "countryId": 1,
      "institutionTypeLkpId": 1,
      "status": "ACTIVE",
      "svgIcon": "data:image/svg+xml;base64,...",
      "dateCreated": "2023-01-01T00:00:00",
      "dateUpdated": "2023-01-01T00:00:00"
    },
    {
      "institutionId": 2,
      "bankName": "Bank of America",
      "swiftCode": "BOFAUS3N",
      "routingNumber": "026009593",
      "ibanPrefix": "US",
      "countryId": 1,
      "institutionTypeLkpId": 1,
      "status": "ACTIVE",
      "svgIcon": "data:image/svg+xml;base64,...",
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
  "totalElements": 120,
  "totalPages": 12,
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

#### Get Bank Institution Code by ID
```
GET /api/v1/bank-institution-codes/{id}
```

Example response:
```json
{
  "institutionId": 1,
  "bankName": "Chase Bank",
  "swiftCode": "CHASUS33",
  "routingNumber": "021000021",
  "ibanPrefix": "US",
  "countryId": 1,
  "institutionTypeLkpId": 1,
  "status": "ACTIVE",
  "svgIcon": "data:image/svg+xml;base64,...",
  "dateCreated": "2023-01-01T00:00:00",
  "dateUpdated": "2023-01-01T00:00:00"
}
```

#### Create Bank Institution Code
```
POST /api/v1/bank-institution-codes
```

Example request body:
```json
{
  "bankName": "Wells Fargo",
  "swiftCode": "WFBIUS6S",
  "routingNumber": "121000248",
  "ibanPrefix": "US",
  "countryId": 1,
  "institutionTypeLkpId": 1,
  "status": "ACTIVE",
  "svgIcon": "data:image/svg+xml;base64,..."
}
```

Example response:
```json
{
  "institutionId": 3,
  "bankName": "Wells Fargo",
  "swiftCode": "WFBIUS6S",
  "routingNumber": "121000248",
  "ibanPrefix": "US",
  "countryId": 1,
  "institutionTypeLkpId": 1,
  "status": "ACTIVE",
  "svgIcon": "data:image/svg+xml;base64,...",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Update Bank Institution Code
```
PUT /api/v1/bank-institution-codes/{id}
```

Example request body:
```json
{
  "bankName": "Wells Fargo Bank",
  "swiftCode": "WFBIUS6S",
  "routingNumber": "121000248",
  "ibanPrefix": "US",
  "countryId": 1,
  "institutionTypeLkpId": 1,
  "status": "ACTIVE",
  "svgIcon": "data:image/svg+xml;base64,..."
}
```

Example response:
```json
{
  "institutionId": 3,
  "bankName": "Wells Fargo Bank",
  "swiftCode": "WFBIUS6S",
  "routingNumber": "121000248",
  "ibanPrefix": "US",
  "countryId": 1,
  "institutionTypeLkpId": 1,
  "status": "ACTIVE",
  "svgIcon": "data:image/svg+xml;base64,...",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T11:45:00"
}
```

#### Delete Bank Institution Code
```
DELETE /api/v1/bank-institution-codes/{id}
```

Response: HTTP 204 No Content

### Holidays API
Endpoints for managing holiday information for different countries and regions.

#### List Holidays
```
GET /api/v1/holidays
```

Example response:
```json
{
  "content": [
    {
      "holidayId": 1,
      "countryId": 1,
      "divisionId": null,
      "holidayName": "New Year's Day",
      "localName": "New Year's Day",
      "holidayDate": "2023-01-01",
      "recurrenceRule": "FREQ=YEARLY;BYMONTH=1;BYMONTHDAY=1",
      "holidayTypeLkpId": 1,
      "businessClosed": true,
      "bankClosed": true,
      "status": "ACTIVE",
      "dateCreated": "2022-12-01T00:00:00",
      "dateUpdated": "2022-12-01T00:00:00"
    },
    {
      "holidayId": 2,
      "countryId": 1,
      "divisionId": null,
      "holidayName": "Independence Day",
      "localName": "Fourth of July",
      "holidayDate": "2023-07-04",
      "recurrenceRule": "FREQ=YEARLY;BYMONTH=7;BYMONTHDAY=4",
      "holidayTypeLkpId": 1,
      "businessClosed": true,
      "bankClosed": true,
      "status": "ACTIVE",
      "dateCreated": "2022-12-01T00:00:00",
      "dateUpdated": "2022-12-01T00:00:00"
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
  "totalElements": 25,
  "totalPages": 3,
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

#### Get Holiday by ID
```
GET /api/v1/holidays/{holidayId}
```

Example response:
```json
{
  "holidayId": 1,
  "countryId": 1,
  "divisionId": null,
  "holidayName": "New Year's Day",
  "localName": "New Year's Day",
  "holidayDate": "2023-01-01",
  "recurrenceRule": "FREQ=YEARLY;BYMONTH=1;BYMONTHDAY=1",
  "holidayTypeLkpId": 1,
  "businessClosed": true,
  "bankClosed": true,
  "status": "ACTIVE",
  "dateCreated": "2022-12-01T00:00:00",
  "dateUpdated": "2022-12-01T00:00:00"
}
```

#### Create Holiday
```
POST /api/v1/holidays
```

Example request body:
```json
{
  "countryId": 1,
  "divisionId": 2,
  "holidayName": "Thanksgiving Day",
  "localName": "Thanksgiving",
  "holidayDate": "2023-11-23",
  "recurrenceRule": "FREQ=YEARLY;BYMONTH=11;BYDAY=4TH",
  "holidayTypeLkpId": 1,
  "businessClosed": true,
  "bankClosed": true,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "holidayId": 3,
  "countryId": 1,
  "divisionId": 2,
  "holidayName": "Thanksgiving Day",
  "localName": "Thanksgiving",
  "holidayDate": "2023-11-23",
  "recurrenceRule": "FREQ=YEARLY;BYMONTH=11;BYDAY=4TH",
  "holidayTypeLkpId": 1,
  "businessClosed": true,
  "bankClosed": true,
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Update Holiday
```
PUT /api/v1/holidays/{holidayId}
```

Example request body:
```json
{
  "countryId": 1,
  "divisionId": 2,
  "holidayName": "Thanksgiving Day",
  "localName": "Thanksgiving Holiday",
  "holidayDate": "2023-11-23",
  "recurrenceRule": "FREQ=YEARLY;BYMONTH=11;BYDAY=4TH",
  "holidayTypeLkpId": 1,
  "businessClosed": true,
  "bankClosed": true,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "holidayId": 3,
  "countryId": 1,
  "divisionId": 2,
  "holidayName": "Thanksgiving Day",
  "localName": "Thanksgiving Holiday",
  "holidayDate": "2023-11-23",
  "recurrenceRule": "FREQ=YEARLY;BYMONTH=11;BYDAY=4TH",
  "holidayTypeLkpId": 1,
  "businessClosed": true,
  "bankClosed": true,
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T11:45:00"
}
```

#### Delete Holiday
```
DELETE /api/v1/holidays/{holidayId}
```

Response: HTTP 204 No Content

### Language Locales API
Endpoints for managing language and locale information.

#### List Language Locales
```
GET /api/v1/language-locales
```

Example response:
```json
{
  "content": [
    {
      "localeId": 1,
      "languageCode": "en",
      "countryCode": "US",
      "localeCode": "en_US",
      "languageName": "English",
      "nativeName": "English",
      "regionName": "United States",
      "rtl": false,
      "sortOrder": 1,
      "status": "ACTIVE",
      "dateCreated": "2023-01-01T00:00:00",
      "dateUpdated": "2023-01-01T00:00:00"
    },
    {
      "localeId": 2,
      "languageCode": "es",
      "countryCode": "ES",
      "localeCode": "es_ES",
      "languageName": "Spanish",
      "nativeName": "Español",
      "regionName": "Spain",
      "rtl": false,
      "sortOrder": 2,
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
  "totalElements": 80,
  "totalPages": 8,
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

#### Get Language Locale by ID
```
GET /api/v1/language-locales/{id}
```

Example response:
```json
{
  "localeId": 1,
  "languageCode": "en",
  "countryCode": "US",
  "localeCode": "en_US",
  "languageName": "English",
  "nativeName": "English",
  "regionName": "United States",
  "rtl": false,
  "sortOrder": 1,
  "status": "ACTIVE",
  "dateCreated": "2023-01-01T00:00:00",
  "dateUpdated": "2023-01-01T00:00:00"
}
```

#### Create Language Locale
```
POST /api/v1/language-locales
```

Example request body:
```json
{
  "languageCode": "fr",
  "countryCode": "FR",
  "localeCode": "fr_FR",
  "languageName": "French",
  "nativeName": "Français",
  "regionName": "France",
  "rtl": false,
  "sortOrder": 3,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "localeId": 3,
  "languageCode": "fr",
  "countryCode": "FR",
  "localeCode": "fr_FR",
  "languageName": "French",
  "nativeName": "Français",
  "regionName": "France",
  "rtl": false,
  "sortOrder": 3,
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Update Language Locale
```
PUT /api/v1/language-locales/{id}
```

Example request body:
```json
{
  "languageCode": "fr",
  "countryCode": "FR",
  "localeCode": "fr_FR",
  "languageName": "French",
  "nativeName": "Français",
  "regionName": "French Republic",
  "rtl": false,
  "sortOrder": 3,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "localeId": 3,
  "languageCode": "fr",
  "countryCode": "FR",
  "localeCode": "fr_FR",
  "languageName": "French",
  "nativeName": "Français",
  "regionName": "French Republic",
  "rtl": false,
  "sortOrder": 3,
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T11:45:00"
}
```

#### Delete Language Locale
```
DELETE /api/v1/language-locales/{id}
```

Response: HTTP 204 No Content

### Lookup Domains and Items API
Endpoints for managing generic lookup values for reference data.

#### Lookup Domains

##### List Lookup Domains
```
GET /api/v1/lookup/domains
```

Example response:
```json
{
  "content": [
    {
      "domainId": 1,
      "domainCode": "BRANCH_TYPE",
      "domainName": "Branch Types",
      "domainDesc": "Types of bank branches",
      "parentDomainId": null,
      "multiselectAllowed": false,
      "hierarchyAllowed": false,
      "tenantOverridable": true,
      "extraJson": null,
      "tenantId": null,
      "status": "ACTIVE"
    },
    {
      "domainId": 2,
      "domainCode": "HOLIDAY_TYPE",
      "domainName": "Holiday Types",
      "domainDesc": "Types of holidays",
      "parentDomainId": null,
      "multiselectAllowed": false,
      "hierarchyAllowed": false,
      "tenantOverridable": true,
      "extraJson": null,
      "tenantId": null,
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
  "totalElements": 15,
  "totalPages": 2,
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

##### Get Lookup Domain by ID
```
GET /api/v1/lookup/domains/{domainId}
```

Example response:
```json
{
  "domainId": 1,
  "domainCode": "BRANCH_TYPE",
  "domainName": "Branch Types",
  "domainDesc": "Types of bank branches",
  "parentDomainId": null,
  "multiselectAllowed": false,
  "hierarchyAllowed": false,
  "tenantOverridable": true,
  "extraJson": null,
  "tenantId": null,
  "status": "ACTIVE"
}
```

##### Create Lookup Domain
```
POST /api/v1/lookup/domains
```

Example request body:
```json
{
  "domainCode": "INSTITUTION_TYPE",
  "domainName": "Institution Types",
  "domainDesc": "Types of financial institutions",
  "parentDomainId": null,
  "multiselectAllowed": false,
  "hierarchyAllowed": false,
  "tenantOverridable": true,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "domainId": 3,
  "domainCode": "INSTITUTION_TYPE",
  "domainName": "Institution Types",
  "domainDesc": "Types of financial institutions",
  "parentDomainId": null,
  "multiselectAllowed": false,
  "hierarchyAllowed": false,
  "tenantOverridable": true,
  "extraJson": null,
  "tenantId": null,
  "status": "ACTIVE"
}
```

##### Update Lookup Domain
```
PUT /api/v1/lookup/domains/{domainId}
```

Example request body:
```json
{
  "domainCode": "INSTITUTION_TYPE",
  "domainName": "Financial Institution Types",
  "domainDesc": "Types of financial institutions",
  "parentDomainId": null,
  "multiselectAllowed": false,
  "hierarchyAllowed": false,
  "tenantOverridable": true,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "domainId": 3,
  "domainCode": "INSTITUTION_TYPE",
  "domainName": "Financial Institution Types",
  "domainDesc": "Types of financial institutions",
  "parentDomainId": null,
  "multiselectAllowed": false,
  "hierarchyAllowed": false,
  "tenantOverridable": true,
  "extraJson": null,
  "tenantId": null,
  "status": "ACTIVE"
}
```

##### Delete Lookup Domain
```
DELETE /api/v1/lookup/domains/{domainId}
```

Response: HTTP 204 No Content

#### Lookup Items

##### List Lookup Items
```
GET /api/v1/lookup/items
```

Example response:
```json
{
  "content": [
    {
      "itemId": 1,
      "domainId": 1,
      "itemCode": "MAIN",
      "itemLabelDefault": "Main Branch",
      "itemDesc": "Main bank branch with full services",
      "parentItemId": null,
      "sortOrder": 1,
      "effectiveFrom": "2023-01-01",
      "effectiveTo": null,
      "isCurrent": true,
      "extraJson": null,
      "tenantId": null,
      "status": "ACTIVE"
    },
    {
      "itemId": 2,
      "domainId": 1,
      "itemCode": "SATELLITE",
      "itemLabelDefault": "Satellite Branch",
      "itemDesc": "Smaller branch with limited services",
      "parentItemId": null,
      "sortOrder": 2,
      "effectiveFrom": "2023-01-01",
      "effectiveTo": null,
      "isCurrent": true,
      "extraJson": null,
      "tenantId": null,
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
  "totalElements": 30,
  "totalPages": 3,
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

##### Get Lookup Items by Domain
```
GET /api/v1/lookup/items/domain/{domainId}
```

Example response:
```json
[
  {
    "itemId": 1,
    "domainId": 1,
    "itemCode": "MAIN",
    "itemLabelDefault": "Main Branch",
    "itemDesc": "Main bank branch with full services",
    "parentItemId": null,
    "sortOrder": 1,
    "effectiveFrom": "2023-01-01",
    "effectiveTo": null,
    "isCurrent": true,
    "extraJson": null,
    "tenantId": null,
    "status": "ACTIVE"
  },
  {
    "itemId": 2,
    "domainId": 1,
    "itemCode": "SATELLITE",
    "itemLabelDefault": "Satellite Branch",
    "itemDesc": "Smaller branch with limited services",
    "parentItemId": null,
    "sortOrder": 2,
    "effectiveFrom": "2023-01-01",
    "effectiveTo": null,
    "isCurrent": true,
    "extraJson": null,
    "tenantId": null,
    "status": "ACTIVE"
  }
]
```

##### Get Lookup Item by ID
```
GET /api/v1/lookup/items/{itemId}
```

Example response:
```json
{
  "itemId": 1,
  "domainId": 1,
  "itemCode": "MAIN",
  "itemLabelDefault": "Main Branch",
  "itemDesc": "Main bank branch with full services",
  "parentItemId": null,
  "sortOrder": 1,
  "effectiveFrom": "2023-01-01",
  "effectiveTo": null,
  "isCurrent": true,
  "extraJson": null,
  "tenantId": null,
  "status": "ACTIVE"
}
```

##### Create Lookup Item
```
POST /api/v1/lookup/items
```

Example request body:
```json
{
  "domainId": 1,
  "itemCode": "ATM",
  "itemLabelDefault": "ATM Location",
  "itemDesc": "ATM-only location",
  "parentItemId": null,
  "sortOrder": 3,
  "effectiveFrom": "2023-01-01",
  "isCurrent": true,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "itemId": 3,
  "domainId": 1,
  "itemCode": "ATM",
  "itemLabelDefault": "ATM Location",
  "itemDesc": "ATM-only location",
  "parentItemId": null,
  "sortOrder": 3,
  "effectiveFrom": "2023-01-01",
  "effectiveTo": null,
  "isCurrent": true,
  "extraJson": null,
  "tenantId": null,
  "status": "ACTIVE"
}
```

##### Update Lookup Item
```
PUT /api/v1/lookup/items/{itemId}
```

Example request body:
```json
{
  "domainId": 1,
  "itemCode": "ATM",
  "itemLabelDefault": "ATM Only",
  "itemDesc": "ATM-only location without staff",
  "parentItemId": null,
  "sortOrder": 3,
  "effectiveFrom": "2023-01-01",
  "isCurrent": true,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "itemId": 3,
  "domainId": 1,
  "itemCode": "ATM",
  "itemLabelDefault": "ATM Only",
  "itemDesc": "ATM-only location without staff",
  "parentItemId": null,
  "sortOrder": 3,
  "effectiveFrom": "2023-01-01",
  "effectiveTo": null,
  "isCurrent": true,
  "extraJson": null,
  "tenantId": null,
  "status": "ACTIVE"
}
```

##### Delete Lookup Item
```
DELETE /api/v1/lookup/items/{itemId}
```

Response: HTTP 204 No Content

### Activity Codes API
Endpoints for managing business activity classification codes.

#### List Activity Codes
```
GET /api/v1/activity-codes
```

Example response:
```json
{
  "content": [
    {
      "activityCodeId": 1,
      "countryId": 1,
      "code": "A01",
      "classificationSys": "NAICS",
      "description": "Crop Production",
      "parentCodeId": null,
      "highRisk": false,
      "riskFactors": null,
      "status": "ACTIVE"
    },
    {
      "activityCodeId": 2,
      "countryId": 1,
      "code": "A02",
      "classificationSys": "NAICS",
      "description": "Animal Production and Aquaculture",
      "parentCodeId": null,
      "highRisk": false,
      "riskFactors": null,
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
  "totalElements": 200,
  "totalPages": 20,
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

#### Get Activity Codes by Country
```
GET /api/v1/activity-codes/country/{countryId}
```

Example response:
```json
[
  {
    "activityCodeId": 1,
    "countryId": 1,
    "code": "A01",
    "classificationSys": "NAICS",
    "description": "Crop Production",
    "parentCodeId": null,
    "highRisk": false,
    "riskFactors": null,
    "status": "ACTIVE"
  },
  {
    "activityCodeId": 2,
    "countryId": 1,
    "code": "A02",
    "classificationSys": "NAICS",
    "description": "Animal Production and Aquaculture",
    "parentCodeId": null,
    "highRisk": false,
    "riskFactors": null,
    "status": "ACTIVE"
  }
]
```

#### Get Child Activity Codes
```
GET /api/v1/activity-codes/parent/{parentCodeId}
```

Example response:
```json
[
  {
    "activityCodeId": 3,
    "countryId": 1,
    "code": "A01.1",
    "classificationSys": "NAICS",
    "description": "Grain and Seed Production",
    "parentCodeId": 1,
    "highRisk": false,
    "riskFactors": null,
    "status": "ACTIVE"
  },
  {
    "activityCodeId": 4,
    "countryId": 1,
    "code": "A01.2",
    "classificationSys": "NAICS",
    "description": "Vegetable and Melon Farming",
    "parentCodeId": 1,
    "highRisk": false,
    "riskFactors": null,
    "status": "ACTIVE"
  }
]
```

#### Get Activity Code by ID
```
GET /api/v1/activity-codes/{activityCodeId}
```

Example response:
```json
{
  "activityCodeId": 1,
  "countryId": 1,
  "code": "A01",
  "classificationSys": "NAICS",
  "description": "Crop Production",
  "parentCodeId": null,
  "highRisk": false,
  "riskFactors": null,
  "status": "ACTIVE"
}
```

#### Create Activity Code
```
POST /api/v1/activity-codes
```

Example request body:
```json
{
  "countryId": 1,
  "code": "A03",
  "classificationSys": "NAICS",
  "description": "Forestry and Logging",
  "parentCodeId": null,
  "highRisk": false,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "activityCodeId": 5,
  "countryId": 1,
  "code": "A03",
  "classificationSys": "NAICS",
  "description": "Forestry and Logging",
  "parentCodeId": null,
  "highRisk": false,
  "riskFactors": null,
  "status": "ACTIVE"
}
```

#### Update Activity Code
```
PUT /api/v1/activity-codes/{activityCodeId}
```

Example request body:
```json
{
  "countryId": 1,
  "code": "A03",
  "classificationSys": "NAICS",
  "description": "Forestry and Logging Operations",
  "parentCodeId": null,
  "highRisk": true,
  "riskFactors": "Environmental impact, safety concerns",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "activityCodeId": 5,
  "countryId": 1,
  "code": "A03",
  "classificationSys": "NAICS",
  "description": "Forestry and Logging Operations",
  "parentCodeId": null,
  "highRisk": true,
  "riskFactors": "Environmental impact, safety concerns",
  "status": "ACTIVE"
}
```

#### Delete Activity Code
```
DELETE /api/v1/activity-codes/{activityCodeId}
```

Response: HTTP 204 No Content

### Consent Catalog API
Endpoints for managing consent catalog entries that define the types of consents that can be given by users.

#### List Consent Catalog
```
GET /api/v1/consent-catalog
```

Example response:
```json
{
  "content": [
    {
      "consentId": 1,
      "consentType": "GDPR",
      "consentDescription": "General Data Protection Regulation consent for processing personal data",
      "expiryPeriodDays": 365,
      "consentVersion": "1.0",
      "consentSource": "WEB",
      "status": "ACTIVE",
      "dateCreated": "2023-01-01T00:00:00",
      "dateUpdated": "2023-01-01T00:00:00"
    },
    {
      "consentId": 2,
      "consentType": "MARKETING",
      "consentDescription": "Consent for receiving marketing communications",
      "expiryPeriodDays": 180,
      "consentVersion": "1.0",
      "consentSource": "MOBILE",
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
  "totalElements": 5,
  "totalPages": 1,
  "last": true,
  "first": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 2,
  "size": 10,
  "number": 0,
  "empty": false
}
```

#### List Consent Catalog by Type
```
GET /api/v1/consent-catalog/type/{consentType}
```

Example response:
```json
{
  "content": [
    {
      "consentId": 1,
      "consentType": "GDPR",
      "consentDescription": "General Data Protection Regulation consent for processing personal data",
      "expiryPeriodDays": 365,
      "consentVersion": "1.0",
      "consentSource": "WEB",
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
  "totalElements": 1,
  "totalPages": 1,
  "last": true,
  "first": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 1,
  "size": 10,
  "number": 0,
  "empty": false
}
```

#### Get Consent Catalog by ID
```
GET /api/v1/consent-catalog/{id}
```

Example response:
```json
{
  "consentId": 1,
  "consentType": "GDPR",
  "consentDescription": "General Data Protection Regulation consent for processing personal data",
  "expiryPeriodDays": 365,
  "consentVersion": "1.0",
  "consentSource": "WEB",
  "status": "ACTIVE",
  "dateCreated": "2023-01-01T00:00:00",
  "dateUpdated": "2023-01-01T00:00:00"
}
```

#### Create Consent Catalog
```
POST /api/v1/consent-catalog
```

Example request body:
```json
{
  "consentType": "COOKIES",
  "consentDescription": "Consent for using cookies on the website",
  "expiryPeriodDays": 90,
  "consentVersion": "1.0",
  "consentSource": "WEB",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "consentId": 3,
  "consentType": "COOKIES",
  "consentDescription": "Consent for using cookies on the website",
  "expiryPeriodDays": 90,
  "consentVersion": "1.0",
  "consentSource": "WEB",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Update Consent Catalog
```
PUT /api/v1/consent-catalog/{id}
```

Example request body:
```json
{
  "consentType": "COOKIES",
  "consentDescription": "Consent for using cookies and similar technologies on the website",
  "expiryPeriodDays": 180,
  "consentVersion": "1.1",
  "consentSource": "WEB",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "consentId": 3,
  "consentType": "COOKIES",
  "consentDescription": "Consent for using cookies and similar technologies on the website",
  "expiryPeriodDays": 180,
  "consentVersion": "1.1",
  "consentSource": "WEB",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T11:45:00"
}
```

#### Delete Consent Catalog
```
DELETE /api/v1/consent-catalog/{id}
```

Response: HTTP 204 No Content

### Title Master API
Endpoints for managing title information (Mr., Mrs., Dr., etc.).

#### List Titles
```
GET /api/v1/titles
```

Example response:
```json
{
  "content": [
    {
      "titleId": 1,
      "prefix": "MR",
      "description": "Mr.",
      "isActive": true,
      "status": "ACTIVE",
      "dateCreated": "2023-01-01T00:00:00",
      "dateUpdated": "2023-01-01T00:00:00"
    },
    {
      "titleId": 2,
      "prefix": "MRS",
      "description": "Mrs.",
      "isActive": true,
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
  "totalElements": 10,
  "totalPages": 1,
  "last": true,
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

#### Get Title by ID
```
GET /api/v1/titles/{titleId}
```

Example response:
```json
{
  "titleId": 1,
  "prefix": "MR",
  "description": "Mr.",
  "isActive": true,
  "status": "ACTIVE",
  "dateCreated": "2023-01-01T00:00:00",
  "dateUpdated": "2023-01-01T00:00:00"
}
```

#### Create Title
```
POST /api/v1/titles
```

Example request body:
```json
{
  "prefix": "DR",
  "description": "Doctor",
  "isActive": true,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "titleId": 3,
  "prefix": "DR",
  "description": "Doctor",
  "isActive": true,
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Update Title
```
PUT /api/v1/titles/{titleId}
```

Example request body:
```json
{
  "prefix": "DR",
  "description": "Doctor (Medical)",
  "isActive": true,
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "titleId": 3,
  "prefix": "DR",
  "description": "Doctor (Medical)",
  "isActive": true,
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T11:45:00"
}
```

#### Delete Title
```
DELETE /api/v1/titles/{titleId}
```

Response: HTTP 204 No Content

### Relationship Type Master API
Endpoints for managing relationship type information (Beneficiary, CEO, etc.).

#### List Relationship Types
```
GET /api/v1/relationship-types
```

Example response:
```json
{
  "content": [
    {
      "relationshipTypeId": 1,
      "code": "BENEFICIARY",
      "description": "Beneficiary",
      "status": "ACTIVE",
      "dateCreated": "2023-01-01T00:00:00",
      "dateUpdated": "2023-01-01T00:00:00"
    },
    {
      "relationshipTypeId": 2,
      "code": "CEO",
      "description": "Chief Executive Officer",
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
  "totalElements": 15,
  "totalPages": 2,
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

#### Get Relationship Type by ID
```
GET /api/v1/relationship-types/{relationshipTypeId}
```

Example response:
```json
{
  "relationshipTypeId": 1,
  "code": "BENEFICIARY",
  "description": "Beneficiary",
  "status": "ACTIVE",
  "dateCreated": "2023-01-01T00:00:00",
  "dateUpdated": "2023-01-01T00:00:00"
}
```

#### Create Relationship Type
```
POST /api/v1/relationship-types
```

Example request body:
```json
{
  "code": "CFO",
  "description": "Chief Financial Officer",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "relationshipTypeId": 3,
  "code": "CFO",
  "description": "Chief Financial Officer",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Update Relationship Type
```
PUT /api/v1/relationship-types/{relationshipTypeId}
```

Example request body:
```json
{
  "code": "CFO",
  "description": "Chief Financial Officer (Finance)",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "relationshipTypeId": 3,
  "code": "CFO",
  "description": "Chief Financial Officer (Finance)",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T11:45:00"
}
```

#### Delete Relationship Type
```
DELETE /api/v1/relationship-types/{relationshipTypeId}
```

Response: HTTP 204 No Content

### Document Template Catalog API
Endpoints for managing document templates that can be used for generating documents in various formats.

#### List Document Templates
```
GET /api/v1/document-templates
```

Example response:
```json
{
  "content": [
    {
      "templateId": 1,
      "templateCode": "INVOICE_TEMPLATE",
      "typeId": 1,
      "templateType": {
        "typeId": 1,
        "typeCode": "PDF",
        "typeName": "PDF Template",
        "description": "Document templates for PDF generation",
        "status": "ACTIVE",
        "dateCreated": "2023-01-01T00:00:00",
        "dateUpdated": "2023-01-01T00:00:00"
      },
      "category": "FINANCIAL",
      "description": "Standard invoice template",
      "templateName": "Standard Invoice",
      "templateContent": "<html><body><h1>INVOICE</h1><p>Customer: ${customerName}</p><p>Amount: ${amount}</p></body></html>",
      "templateVariables": {
        "customerName": "string",
        "invoiceNumber": "string",
        "amount": "number",
        "items": "array"
      },
      "version": "1.0",
      "status": "ACTIVE",
      "dateCreated": "2023-06-15T10:30:00",
      "dateUpdated": "2023-06-15T10:30:00"
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
  "totalElements": 5,
  "totalPages": 1,
  "last": true,
  "first": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 1,
  "size": 10,
  "number": 0,
  "empty": false
}
```

#### List Document Templates by Category
```
GET /api/v1/document-templates/category/{category}
```

Example response:
```json
{
  "content": [
    {
      "templateId": 1,
      "templateCode": "INVOICE_TEMPLATE",
      "typeId": 1,
      "templateType": {
        "typeId": 1,
        "typeCode": "PDF",
        "typeName": "PDF Template",
        "description": "Document templates for PDF generation",
        "status": "ACTIVE",
        "dateCreated": "2023-01-01T00:00:00",
        "dateUpdated": "2023-01-01T00:00:00"
      },
      "category": "FINANCIAL",
      "description": "Standard invoice template",
      "templateName": "Standard Invoice",
      "templateContent": "<html><body><h1>INVOICE</h1><p>Customer: ${customerName}</p><p>Amount: ${amount}</p></body></html>",
      "templateVariables": {
        "customerName": "string",
        "invoiceNumber": "string",
        "amount": "number",
        "items": "array"
      },
      "version": "1.0",
      "status": "ACTIVE",
      "dateCreated": "2023-06-15T10:30:00",
      "dateUpdated": "2023-06-15T10:30:00"
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
  "totalElements": 1,
  "totalPages": 1,
  "last": true,
  "first": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 1,
  "size": 10,
  "number": 0,
  "empty": false
}
```

#### List Document Templates by Type
```
GET /api/v1/document-templates/type/{typeId}
```

Example response:
```json
{
  "content": [
    {
      "templateId": 1,
      "templateCode": "INVOICE_TEMPLATE",
      "typeId": 1,
      "templateType": {
        "typeId": 1,
        "typeCode": "PDF",
        "typeName": "PDF Template",
        "description": "Document templates for PDF generation",
        "status": "ACTIVE",
        "dateCreated": "2023-01-01T00:00:00",
        "dateUpdated": "2023-01-01T00:00:00"
      },
      "category": "FINANCIAL",
      "description": "Standard invoice template",
      "templateName": "Standard Invoice",
      "templateContent": "<html><body><h1>INVOICE</h1><p>Customer: ${customerName}</p><p>Amount: ${amount}</p></body></html>",
      "templateVariables": {
        "customerName": "string",
        "invoiceNumber": "string",
        "amount": "number",
        "items": "array"
      },
      "version": "1.0",
      "status": "ACTIVE",
      "dateCreated": "2023-06-15T10:30:00",
      "dateUpdated": "2023-06-15T10:30:00"
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
  "totalElements": 1,
  "totalPages": 1,
  "last": true,
  "first": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 1,
  "size": 10,
  "number": 0,
  "empty": false
}
```

#### Get Document Template by ID
```
GET /api/v1/document-templates/{templateId}
```

Example response:
```json
{
  "templateId": 1,
  "templateCode": "INVOICE_TEMPLATE",
  "typeId": 1,
  "templateType": {
    "typeId": 1,
    "typeCode": "PDF",
    "typeName": "PDF Template",
    "description": "Document templates for PDF generation",
    "status": "ACTIVE",
    "dateCreated": "2023-01-01T00:00:00",
    "dateUpdated": "2023-01-01T00:00:00"
  },
  "category": "FINANCIAL",
  "description": "Standard invoice template",
  "templateName": "Standard Invoice",
  "templateContent": "<html><body><h1>INVOICE</h1><p>Customer: ${customerName}</p><p>Amount: ${amount}</p></body></html>",
  "templateVariables": {
    "customerName": "string",
    "invoiceNumber": "string",
    "amount": "number",
    "items": "array"
  },
  "version": "1.0",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Get Document Template by Code
```
GET /api/v1/document-templates/code/{templateCode}
```

Example response:
```json
{
  "templateId": 1,
  "templateCode": "INVOICE_TEMPLATE",
  "typeId": 1,
  "templateType": {
    "typeId": 1,
    "typeCode": "PDF",
    "typeName": "PDF Template",
    "description": "Document templates for PDF generation",
    "status": "ACTIVE",
    "dateCreated": "2023-01-01T00:00:00",
    "dateUpdated": "2023-01-01T00:00:00"
  },
  "category": "FINANCIAL",
  "description": "Standard invoice template",
  "templateName": "Standard Invoice",
  "templateContent": "<html><body><h1>INVOICE</h1><p>Customer: ${customerName}</p><p>Amount: ${amount}</p></body></html>",
  "templateVariables": {
    "customerName": "string",
    "invoiceNumber": "string",
    "amount": "number",
    "items": "array"
  },
  "version": "1.0",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Create Document Template
```
POST /api/v1/document-templates
```

Example request body:
```json
{
  "templateCode": "RECEIPT_TEMPLATE",
  "typeId": 1,
  "category": "FINANCIAL",
  "description": "Standard receipt template",
  "templateName": "Standard Receipt",
  "templateContent": "<html><body><h1>RECEIPT</h1><p>Customer: ${customerName}</p><p>Amount: ${amount}</p></body></html>",
  "templateVariables": {
    "customerName": "string",
    "receiptNumber": "string",
    "amount": "number",
    "items": "array"
  },
  "version": "1.0",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "templateId": 2,
  "templateCode": "RECEIPT_TEMPLATE",
  "typeId": 1,
  "templateType": {
    "typeId": 1,
    "typeCode": "PDF",
    "typeName": "PDF Template",
    "description": "Document templates for PDF generation",
    "status": "ACTIVE",
    "dateCreated": "2023-01-01T00:00:00",
    "dateUpdated": "2023-01-01T00:00:00"
  },
  "category": "FINANCIAL",
  "description": "Standard receipt template",
  "templateName": "Standard Receipt",
  "templateContent": "<html><body><h1>RECEIPT</h1><p>Customer: ${customerName}</p><p>Amount: ${amount}</p></body></html>",
  "templateVariables": {
    "customerName": "string",
    "receiptNumber": "string",
    "amount": "number",
    "items": "array"
  },
  "version": "1.0",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Update Document Template
```
PUT /api/v1/document-templates/{templateId}
```

Example request body:
```json
{
  "templateCode": "RECEIPT_TEMPLATE",
  "typeId": 1,
  "category": "FINANCIAL",
  "description": "Standard receipt template with tax information",
  "templateName": "Standard Receipt",
  "templateContent": "<html><body><h1>RECEIPT</h1><p>Customer: ${customerName}</p><p>Amount: ${amount}</p><p>Tax: ${tax}</p></body></html>",
  "templateVariables": {
    "customerName": "string",
    "receiptNumber": "string",
    "amount": "number",
    "tax": "number",
    "items": "array"
  },
  "version": "1.1",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "templateId": 2,
  "templateCode": "RECEIPT_TEMPLATE",
  "typeId": 1,
  "templateType": {
    "typeId": 1,
    "typeCode": "PDF",
    "typeName": "PDF Template",
    "description": "Document templates for PDF generation",
    "status": "ACTIVE",
    "dateCreated": "2023-01-01T00:00:00",
    "dateUpdated": "2023-01-01T00:00:00"
  },
  "category": "FINANCIAL",
  "description": "Standard receipt template with tax information",
  "templateName": "Standard Receipt",
  "templateContent": "<html><body><h1>RECEIPT</h1><p>Customer: ${customerName}</p><p>Amount: ${amount}</p><p>Tax: ${tax}</p></body></html>",
  "templateVariables": {
    "customerName": "string",
    "receiptNumber": "string",
    "amount": "number",
    "tax": "number",
    "items": "array"
  },
  "version": "1.1",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T11:45:00"
}
```

#### Delete Document Template
```
DELETE /api/v1/document-templates/{templateId}
```

Response: HTTP 204 No Content

### Document Template Type Catalog API
Endpoints for managing document template types (PDF, DOCX, HTML, etc.).

#### List Document Template Types
```
GET /api/v1/document-template-types
```

Example response:
```json
{
  "content": [
    {
      "typeId": 1,
      "typeCode": "PDF",
      "typeName": "PDF Template",
      "description": "Document templates for PDF generation",
      "status": "ACTIVE",
      "dateCreated": "2023-01-01T00:00:00",
      "dateUpdated": "2023-01-01T00:00:00"
    },
    {
      "typeId": 2,
      "typeCode": "DOCX",
      "typeName": "Word Template",
      "description": "Document templates for Microsoft Word documents",
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
  "totalElements": 4,
  "totalPages": 1,
  "last": true,
  "first": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 4,
  "size": 10,
  "number": 0,
  "empty": false
}
```

#### Get Document Template Type by ID
```
GET /api/v1/document-template-types/{typeId}
```

Example response:
```json
{
  "typeId": 1,
  "typeCode": "PDF",
  "typeName": "PDF Template",
  "description": "Document templates for PDF generation",
  "status": "ACTIVE",
  "dateCreated": "2023-01-01T00:00:00",
  "dateUpdated": "2023-01-01T00:00:00"
}
```

#### Get Document Template Type by Code
```
GET /api/v1/document-template-types/code/{typeCode}
```

Example response:
```json
{
  "typeId": 1,
  "typeCode": "PDF",
  "typeName": "PDF Template",
  "description": "Document templates for PDF generation",
  "status": "ACTIVE",
  "dateCreated": "2023-01-01T00:00:00",
  "dateUpdated": "2023-01-01T00:00:00"
}
```

#### Create Document Template Type
```
POST /api/v1/document-template-types
```

Example request body:
```json
{
  "typeCode": "CSV",
  "typeName": "CSV Template",
  "description": "Document templates for CSV files",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "typeId": 5,
  "typeCode": "CSV",
  "typeName": "CSV Template",
  "description": "Document templates for CSV files",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Update Document Template Type
```
PUT /api/v1/document-template-types/{typeId}
```

Example request body:
```json
{
  "typeCode": "CSV",
  "typeName": "CSV Template",
  "description": "Document templates for CSV and TSV files",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "typeId": 5,
  "typeCode": "CSV",
  "typeName": "CSV Template",
  "description": "Document templates for CSV and TSV files",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T11:45:00"
}
```

#### Delete Document Template Type
```
DELETE /api/v1/document-template-types/{typeId}
```

Response: HTTP 204 No Content

### Notification Message Catalog API
Endpoints for managing notification message catalog entries that define the types of notification messages that can be sent to users based on events, with support for localization and HTML templates.

#### List Notification Messages
```
GET /api/v1/notification-messages
```

Example response:
```json
{
  "content": [
    {
      "messageId": 1,
      "messageCode": "LOW_BALANCE",
      "typeId": 1,
      "messageType": {
        "typeId": 1,
        "typeCode": "EMAIL",
        "typeName": "Email Message",
        "description": "Notification messages sent via email",
        "status": "ACTIVE",
        "dateCreated": "2023-06-15T10:30:00",
        "dateUpdated": "2023-06-15T10:30:00"
      },
      "eventType": "ACCOUNT_BALANCE",
      "description": "Notification for low account balance",
      "defaultSubject": "Low Balance Alert",
      "defaultMessage": "Your account balance is below {threshold}",
      "parameters": {
        "threshold": "number"
      },
      "status": "ACTIVE",
      "dateCreated": "2023-06-15T10:30:00",
      "dateUpdated": "2023-06-15T10:30:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalElements": 1,
  "totalPages": 1,
  "last": true,
  "size": 10,
  "number": 0,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 1,
  "first": true,
  "empty": false
}
```

#### Get Notification Message by ID
```
GET /api/v1/notification-messages/{messageId}
```

Example response:
```json
{
  "messageId": 1,
  "messageCode": "LOW_BALANCE",
  "messageType": "EMAIL",
  "eventType": "ACCOUNT_BALANCE",
  "description": "Notification for low account balance",
  "defaultSubject": "Low Balance Alert",
  "defaultMessage": "Your account balance is below {threshold}",
  "parameters": {
    "threshold": "number"
  },
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Create Notification Message
```
POST /api/v1/notification-messages
```

Example request body:
```json
{
  "messageCode": "ACCOUNT_LOCKED",
  "messageType": "SMS",
  "eventType": "SECURITY",
  "description": "Notification for account locked due to multiple failed login attempts",
  "defaultSubject": "Account Security Alert",
  "defaultMessage": "Your account has been locked due to {attempts} failed login attempts. Please contact support.",
  "parameters": {
    "attempts": "number"
  },
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "messageId": 2,
  "messageCode": "ACCOUNT_LOCKED",
  "messageType": "SMS",
  "eventType": "SECURITY",
  "description": "Notification for account locked due to multiple failed login attempts",
  "defaultSubject": "Account Security Alert",
  "defaultMessage": "Your account has been locked due to {attempts} failed login attempts. Please contact support.",
  "parameters": {
    "attempts": "number"
  },
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T11:30:00",
  "dateUpdated": "2023-06-15T11:30:00"
}
```

#### Update Notification Message
```
PUT /api/v1/notification-messages/{messageId}
```

Example request body:
```json
{
  "messageCode": "ACCOUNT_LOCKED",
  "typeId": 2,
  "messageType": {
    "typeId": 2,
    "typeCode": "SMS",
    "typeName": "SMS Message",
    "description": "Notification messages sent via SMS",
    "status": "ACTIVE"
  },
  "eventType": "SECURITY",
  "description": "Notification for account locked due to security concerns",
  "defaultSubject": "Account Security Alert",
  "defaultMessage": "Your account has been locked due to {reason}. Please contact support at {supportPhone}.",
  "parameters": {
    "reason": "string",
    "supportPhone": "string"
  },
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "messageId": 2,
  "messageCode": "ACCOUNT_LOCKED",
  "typeId": 2,
  "messageType": {
    "typeId": 2,
    "typeCode": "SMS",
    "typeName": "SMS Message",
    "description": "Notification messages sent via SMS",
    "status": "ACTIVE",
    "dateCreated": "2023-06-15T11:30:00",
    "dateUpdated": "2023-06-15T11:30:00"
  },
  "eventType": "SECURITY",
  "description": "Notification for account locked due to security concerns",
  "defaultSubject": "Account Security Alert",
  "defaultMessage": "Your account has been locked due to {reason}. Please contact support at {supportPhone}.",
  "parameters": {
    "reason": "string",
    "supportPhone": "string"
  },
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T11:30:00",
  "dateUpdated": "2023-06-15T12:45:00"
}
```

#### Delete Notification Message
```
DELETE /api/v1/notification-messages/{messageId}
```

Response: HTTP 204 No Content

#### Notification Message Templates API

Endpoints for managing HTML templates for notification messages.

```
GET /api/v1/notification-templates/message/{messageId}
```

Example response:
```json
[
  {
    "templateId": 1,
    "messageId": 1,
    "templateName": "low_balance_email",
    "templateContent": "<html><body><h1>Low Balance Alert</h1><p>Your account balance is below ${threshold}.</p></body></html>",
    "templateType": "HTML",
    "version": "1.0",
    "status": "ACTIVE",
    "dateCreated": "2023-06-15T10:30:00",
    "dateUpdated": "2023-06-15T10:30:00"
  }
]
```

#### Notification Message Localizations API

Endpoints for managing localized versions of notification messages.

```
GET /api/v1/notification-localizations/message/{messageId}
```

Example response:
```json
[
  {
    "localizationId": 1,
    "messageId": 1,
    "localeId": 2,
    "subject": "Alerta de Saldo Bajo",
    "message": "Su saldo de cuenta está por debajo de {threshold}.",
    "status": "ACTIVE",
    "dateCreated": "2023-06-15T10:30:00",
    "dateUpdated": "2023-06-15T10:30:00"
  }
]
```

### Message Type Catalog API
Endpoints for managing message type catalog entries that define the types of notification messages that can be sent (Email, SMS, Push, etc.).

#### List Message Types
```
GET /api/v1/message-types
```

Example response:
```json
{
  "content": [
    {
      "typeId": 1,
      "typeCode": "EMAIL",
      "typeName": "Email Message",
      "description": "Notification messages sent via email",
      "status": "ACTIVE",
      "dateCreated": "2023-06-15T10:30:00",
      "dateUpdated": "2023-06-15T10:30:00"
    },
    {
      "typeId": 2,
      "typeCode": "SMS",
      "typeName": "SMS Message",
      "description": "Notification messages sent via SMS",
      "status": "ACTIVE",
      "dateCreated": "2023-06-15T10:30:00",
      "dateUpdated": "2023-06-15T10:30:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalElements": 2,
  "totalPages": 1,
  "last": true,
  "size": 10,
  "number": 0,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```

#### Get Message Type by ID
```
GET /api/v1/message-types/{typeId}
```

Example response:
```json
{
  "typeId": 1,
  "typeCode": "EMAIL",
  "typeName": "Email Message",
  "description": "Notification messages sent via email",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T10:30:00",
  "dateUpdated": "2023-06-15T10:30:00"
}
```

#### Create Message Type
```
POST /api/v1/message-types
```

Example request body:
```json
{
  "typeCode": "PUSH",
  "typeName": "Push Notification",
  "description": "Notification messages sent as mobile push notifications",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "typeId": 3,
  "typeCode": "PUSH",
  "typeName": "Push Notification",
  "description": "Notification messages sent as mobile push notifications",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T11:30:00",
  "dateUpdated": "2023-06-15T11:30:00"
}
```

#### Update Message Type
```
PUT /api/v1/message-types/{typeId}
```

Example request body:
```json
{
  "typeCode": "PUSH",
  "typeName": "Mobile Push Notification",
  "description": "Notification messages sent as mobile push notifications",
  "status": "ACTIVE"
}
```

Example response:
```json
{
  "typeId": 3,
  "typeCode": "PUSH",
  "typeName": "Mobile Push Notification",
  "description": "Notification messages sent as mobile push notifications",
  "status": "ACTIVE",
  "dateCreated": "2023-06-15T11:30:00",
  "dateUpdated": "2023-06-15T12:45:00"
}
```

#### Delete Message Type
```
DELETE /api/v1/message-types/{typeId}
```

Response: HTTP 204 No Content

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
