# Common Platform Reference Master Data

## Overview
The Common Platform Reference Master Data microservice is a core component of the Firefly platform, responsible for managing and providing access to various types of reference and master data used across the platform. This microservice provides a centralized repository for commonly used data such as countries, currencies, bank codes, and other reference information.

## Features
- RESTful API for managing reference data
- Reactive programming model using Spring WebFlux
- Comprehensive data model for various types of master data
- Versioned API endpoints
- OpenAPI/Swagger documentation
- Containerized deployment

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

## API Endpoints
The microservice provides RESTful API endpoints for managing and retrieving reference data. All endpoints follow a versioned structure (e.g., `/api/v1/...`).

Examples of available endpoints:
- `/api/v1/activity-codes`: Manage activity codes
- `/api/v1/countries`: Manage country information
- `/api/v1/currencies`: Manage currency information
- `/api/v1/holidays`: Manage holiday information
- `/api/v1/language-locales`: Manage language and locale information

For detailed API documentation, access the Swagger UI at `/swagger-ui.html` when the application is running.

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