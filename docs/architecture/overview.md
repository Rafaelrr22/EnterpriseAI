# EnterpriseAI - Architecture Overview

## Introduction

EnterpriseAI is a self-hosted enterprise AI platform designed to help organizations search, understand and interact with internal knowledge using natural language.

The platform combines Retrieval-Augmented Generation (RAG), local Large Language Models (LLMs) and a modular software architecture to provide secure and intelligent access to company documentation.

---

## Vision

EnterpriseAI aims to become a centralized knowledge platform where employees can:

- Search internal documentation
- Ask questions using natural language
- Upload and manage company documents
- Retrieve trustworthy answers based on indexed content
- Keep all sensitive information inside the organization

---

## Technology Stack

### Frontend
- Angular

### Backend
- Spring Boot

### AI Runtime
- Ollama

### Large Language Model
- Qwen 3

### Vector Database
- Qdrant

### Relational Database
- PostgreSQL

### Containerization
- Docker

### Documentation
- Markdown

### Version Control
- Git & GitHub

---

## High-Level Architecture

The platform follows a modular architecture where each component has a single responsibility.

```text
Angular UI
        │
        ▼
Spring Boot REST API
        │
 ┌──────┼────────┐
 ▼      ▼        ▼
Auth   AI    Documents
 │      │        │
 ▼      ▼        ▼
PostgreSQL Ollama Qdrant
```

This architecture promotes:

- Scalability
- Maintainability
- Loose coupling
- Easy extensibility

---

## Core Modules

The system is composed of the following modules:

- Authentication
- User Management
- AI Service
- Chat
- Document Management
- Retrieval-Augmented Generation (RAG)
- Knowledge Search

Each module is designed to operate independently while communicating through the Spring Boot REST API.

---

## RAG Workflow

```text
PDF / DOCX
      │
      ▼
Document Parser
      │
      ▼
Text Chunking
      │
      ▼
Embeddings
      │
      ▼
Qdrant
      │
      ▼
Semantic Search
      │
      ▼
Relevant Context
      │
      ▼
Ollama
      │
      ▼
Final Response
```

---

## Design Principles

EnterpriseAI follows several architectural principles:

- Self-hosted by design
- Modular architecture
- Security first
- Privacy focused
- Enterprise ready
- Easy to maintain
- Easily extensible

---

## Next Steps

The following documents provide additional architectural details:

- backend-architecture.md
- modules.md
- database-model.md
- use-cases.md

Detailed diagrams will be created during Sprint 2.