# Template Catalog System

This document provides an overview of the Template Catalog System, which includes both Notification Templates and Document Templates.

## Overview

The Template Catalog System provides a flexible way to manage and use templates for various purposes:

1. **Notification Templates**: Used for sending notifications via different channels (email, SMS, push, etc.)
2. **Document Templates**: Used for generating documents in various formats (PDF, DOCX, HTML, etc.)

Both systems support:
- Template categorization
- Template variables for dynamic content
- Localization for multiple languages
- Version tracking
- Full CRUD operations via REST API

## Notification Template System

### Components

1. **MessageTypeCatalog**: Defines the types of notification messages (Email, SMS, Push, etc.)
2. **NotificationMessageCatalog**: Defines the notification messages that can be sent
3. **NotificationMessageTemplate**: Stores the HTML templates for notification messages
4. **NotificationMessageLocalization**: Stores localized versions of notification messages

### Usage Examples

#### Creating a Notification Message

```json
POST /api/v1/notification-messages
{
  "messageCode": "ACCOUNT_BALANCE_LOW",
  "typeId": 1,
  "eventType": "ACCOUNT_ALERT",
  "description": "Notification sent when account balance is below threshold",
  "defaultSubject": "Low Balance Alert",
  "defaultMessage": "Your account balance is below the threshold.",
  "parameters": {
    "threshold": "number",
    "accountNumber": "string",
    "balance": "number"
  },
  "status": "ACTIVE"
}
```

#### Creating a Notification Template

```json
POST /api/v1/notification-templates
{
  "messageId": 1,
  "templateName": "low_balance_email",
  "templateContent": "<html><body><h1>Low Balance Alert</h1><p>Your account balance is below ${threshold}.</p></body></html>",
  "templateType": "HTML",
  "version": "1.0",
  "templateVariables": {
    "threshold": "1000",
    "accountNumber": "123456789",
    "balance": "950"
  },
  "status": "ACTIVE"
}
```

#### Creating a Localized Notification Message

```json
POST /api/v1/notification-localizations
{
  "messageId": 1,
  "localeId": 2,
  "subject": "Alerta de saldo bajo",
  "message": "Su saldo de cuenta está por debajo del umbral.",
  "status": "ACTIVE"
}
```

#### Retrieving Templates for a Message

```
GET /api/v1/notification-templates/message/1
```

## Document Template System

### Components

1. **DocumentTemplateTypeCatalog**: Defines the types of document templates (PDF, DOCX, HTML, etc.)
2. **DocumentTemplateCatalog**: Stores the document templates
3. **DocumentTemplateLocalization**: Stores localized versions of document templates

### Usage Examples

#### Creating a Document Template Type

```json
POST /api/v1/document-template-types
{
  "typeCode": "PDF",
  "typeName": "PDF Template",
  "description": "Document templates for PDF generation",
  "status": "ACTIVE"
}
```

#### Creating a Document Template

```json
POST /api/v1/document-templates
{
  "templateCode": "INVOICE_TEMPLATE",
  "typeId": 1,
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
  "status": "ACTIVE"
}
```

#### Creating a Localized Document Template

```json
POST /api/v1/document-template-localizations
{
  "templateId": 1,
  "localeId": 2,
  "templateName": "Factura Estándar",
  "templateContent": "<html><body><h1>FACTURA</h1><p>Cliente: ${customerName}</p><p>Monto: ${amount}</p></body></html>",
  "status": "ACTIVE"
}
```

#### Retrieving Templates by Category

```
GET /api/v1/document-templates/category/FINANCIAL
```

## Best Practices

1. **Template Variables**: Use consistent naming for template variables
2. **Localization**: Always provide default templates before adding localizations
3. **Versioning**: Increment version numbers when making significant changes to templates
4. **Categories**: Use meaningful categories to organize templates
5. **Template Content**: Use valid markup (HTML, XML, etc.) in template content

## Error Handling

The API returns appropriate HTTP status codes:
- 200: Success
- 201: Created
- 204: No Content (for successful deletion)
- 400: Bad Request
- 404: Not Found
- 500: Internal Server Error

Error responses include a message explaining the error.

## Security Considerations

- Templates should be validated before storage to prevent injection attacks
- Access to template management should be restricted to authorized users
- Template variables should be sanitized before rendering
