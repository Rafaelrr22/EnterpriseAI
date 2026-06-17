# Backend Architecture

## Overview

The EnterpriseAI backend follows a **Package by Feature** architecture instead of the traditional **Package by Layer** approach.

This design groups related classes by business domain rather than by technical responsibility, making the application easier to understand, maintain and extend.

---

## Why Package by Feature?

Traditional Spring Boot projects usually organize code like this:

```
controller/
service/
repository/
entity/
dto/
```

While simple, this structure becomes difficult to maintain as the application grows.

EnterpriseAI groups components by feature instead.

Example:

```
chat/
    controller/
    service/
    repository/
    entity/
    dto/
```

Everything related to the Chat module lives in one place.

---

## Project Structure

```text
com.enterpriseai
│
├── config
├── security
├── common
├── ai
├── chat
├── document
├── rag
├── user
└── health
```

---

## Core Packages

### config

Contains application configuration classes.

Examples:

- Security configuration
- Swagger configuration
- Bean configuration
- CORS configuration

---

### security

Responsible for application security.

Includes:

- JWT
- Authentication
- Authorization
- UserDetailsService

---

### common

Shared code used across the application.

Contains:

- DTOs
- Exceptions
- Constants
- Utilities
- Validation
- Mappers

---

### ai

Handles communication with the LLM.

Responsibilities:

- Prompt generation
- AI requests
- AI responses
- Ollama integration

---

### chat

Responsible for conversations and messages.

---

### document

Responsible for:

- Upload
- Parsing
- Metadata
- Storage

---

### rag

Responsible for:

- Chunking
- Embeddings
- Vector search
- Context retrieval

---

### user

Responsible for:

- Users
- Roles
- Permissions

---

### health

Application health endpoints.

Used for:

- Docker
- Monitoring
- Production deployments

---

## Benefits

This architecture provides:

- Better maintainability
- Better scalability
- Easier onboarding
- Clear separation of responsibilities
- Easier testing
- Reduced coupling