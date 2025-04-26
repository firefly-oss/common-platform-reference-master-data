# Firefly Platform - Reference Master Data Microservice

## Overview

The Reference Master Data Microservice is a core component of the Firefly Platform, providing centralized management of reference data used across the platform. This microservice serves as the single source of truth for various types of master data, ensuring consistency and standardization across all platform services.

Built with a reactive architecture using Spring WebFlux and R2DBC, this microservice offers high-performance, non-blocking APIs for managing and retrieving reference data.

## Key Features

- **Comprehensive Master Data Management**: Centralized repository for all reference data
- **Reactive Architecture**: Non-blocking APIs built with Spring WebFlux and R2DBC
- **Catalog-Based Design**: Flexible catalog entities for dynamic configuration
- **Internationalization Support**: Localization capabilities for global deployments
- **Pagination and Filtering**: Efficient data retrieval with pagination and filtering options
- **OpenAPI Documentation**: Comprehensive API documentation with Swagger UI
- **Database Migration**: Automated schema management with Flyway
- **Monitoring and Health Checks**: Production-ready with Spring Actuator endpoints

## Modules

The microservice is organized into the following modules:

- **common-platform-reference-master-data-core**: Core business logic and service implementations
- **common-platform-reference-master-data-models**: Data models, entities, and repositories
- **common-platform-reference-master-data-interfaces**: DTOs and service interfaces
- **common-platform-reference-master-data-web**: REST controllers and web configuration

## Master Data Catalogs

The microservice manages the following types of master data:

### Geographic Data
- **Countries**: ISO codes, names, regions, and status
- **Administrative Divisions**: States, provinces, and other administrative regions
- **Branches**: Physical locations and branches

### Financial Data
- **Currencies**: ISO codes, names, symbols, and decimal precision
- **Bank Institution Codes**: Bank identification codes and routing numbers
- **Holidays**: Financial and business holidays by country and region

### Identity and Relationships
- **Identity Document Catalog**: Types of identity documents with validation rules
- **Identity Document Categories**: Categorization of identity documents
- **Relationship Types**: Defines relationship types between entities
- **Titles**: Personal titles (Mr., Mrs., Dr., etc.)

### Communication and Notifications
- **Notification Message Catalog**: Templates for notifications across different channels
- **Message Type Catalog**: Types of messages (Email, SMS, Push, In-App)
- **Document Templates**: Templates for generating documents

### Miscellaneous
- **Legal Forms**: Types of legal entities and business structures
- **Language Locales**: Supported languages and locales
- **Consent Catalog**: Types of consents and their descriptions
- **Activity Codes**: Business activity classification codes
- **Lookup Domains and Items**: Generic lookup values for various domains

## Technical Stack

- **Java 17**: Modern language features
- **Spring Boot 3.x**: Application framework
- **Spring WebFlux**: Reactive web framework
- **R2DBC**: Reactive database connectivity
- **PostgreSQL**: Primary database
- **Flyway**: Database migration
- **SpringDoc OpenAPI**: API documentation
- **JUnit 5 & Mockito**: Testing framework
- **Project Reactor**: Reactive programming library

## Getting Started

### Prerequisites

- Java 17 or higher
- PostgreSQL 13 or higher
- Maven 3.8 or higher

### Environment Variables

The following environment variables need to be set:

```
DB_HOST=localhost
DB_PORT=5432
DB_NAME=master_data
DB_USERNAME=postgres
DB_PASSWORD=postgres
DB_SSL_MODE=disable
```

### Building the Application

```bash
mvn clean install
```

### Running the Application

```bash
mvn spring-boot:run -pl common-platform-reference-master-data-web
```

Or using the JAR file:

```bash
java -jar common-platform-reference-master-data-web/target/common-platform-reference-master-data-web-1.0.0-SNAPSHOT.jar
```

### API Documentation

Once the application is running, you can access the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

## Testing

The microservice includes a comprehensive test suite covering all service implementations. Tests are organized to match the package structure of the main application code.

To run the tests:

```bash
mvn test
```

## Deployment

The application is containerized and can be deployed using Docker:

```bash
docker build -t firefly-master-data .
docker run -p 8080:8080 --env-file .env firefly-master-data
```

## Contributing

Please refer to the contribution guidelines for details on how to contribute to this project.

## License

This project is proprietary and confidential. Unauthorized copying, transferring, or reproduction of the contents of this repository, via any medium, is strictly prohibited.

## Contact

For any inquiries, please contact the Catalis Development Team at dev@catalis.com.
