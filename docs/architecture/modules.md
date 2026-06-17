# EnterpriseAI - System Modules

## Overview

EnterpriseAI follows a modular architecture where every module has a single responsibility.

This approach improves maintainability, scalability and allows each module to evolve independently.

---

# Authentication Module

## Responsibilities

- Authenticate users
- Issue JWT tokens
- Validate user credentials
- Manage roles and permissions

## Technologies

- Spring Security
- JWT
- PostgreSQL

---

# User Module

## Responsibilities

- Manage users
- User profiles
- Password management
- Roles
- Permissions

## Technologies

- Spring Boot
- PostgreSQL

---

# Chat Module

## Responsibilities

- Manage conversations
- Store chat history
- Create new chats
- Store messages

## Technologies

- Spring Boot
- PostgreSQL

---

# AI Module

## Responsibilities

- Communicate with Ollama
- Build prompts
- Process AI requests
- Return AI responses

## Technologies

- Ollama
- REST API

---

# Document Module

## Responsibilities

- Upload documents
- Store metadata
- Parse PDF
- Parse DOCX
- Manage document versions

## Technologies

- Spring Boot
- Apache PDFBox
- Apache POI

---

# RAG Module

## Responsibilities

- Split documents into chunks
- Generate embeddings
- Store vectors
- Semantic search
- Retrieve context

## Technologies

- Qdrant
- Ollama Embeddings

---

# Common Module

## Responsibilities

Shared components used by the entire application.

Includes:

- DTOs
- Exceptions
- Utilities
- Constants
- Validation
- Mappers

---

# Health Module

## Responsibilities

Provide application health information.

Endpoints:

- /health

Used by:

- Docker
- Monitoring
- Future Kubernetes deployments

---

# Module Relationships

```text
Angular

↓

Authentication

↓

Chat

↓

AI

↓

Document

↓

RAG

↓

Ollama
```

Every module communicates through the Spring Boot backend while keeping responsibilities isolated.