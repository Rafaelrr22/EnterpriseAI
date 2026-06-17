# Database Model

## Overview

The EnterpriseAI database model is designed around the application's core business entities.

The first version focuses on user management, document management and AI conversations while keeping the architecture modular and extensible.

All entities will use UUID as the primary identifier.

---

## Core Entities

The initial version of EnterpriseAI is composed of the following entities:

- User
- Role
- Document
- DocumentChunk
- Chat
- Message

Additional entities will be introduced as the platform evolves.

---

## User

### Description

Represents an authenticated user of the platform.

### Responsibilities

- Authenticate into the platform
- Own conversations
- Upload documents
- Access features according to assigned roles

### Main Attributes

- id (UUID)
- username
- email
- password
- enabled
- createdAt

---

## Role

### Description

Defines the permissions assigned to users.

### Main Attributes

- id (UUID)
- name

### Default Roles

- ADMIN
- USER

---

## Document

### Description

Represents a document uploaded to the knowledge base.

### Responsibilities

- Store metadata
- Track uploaded files
- Associate documents with users

### Main Attributes

- id (UUID)
- filename
- contentType
- uploadedBy
- uploadDate
- status

A document may generate multiple document chunks.

---

## DocumentChunk

### Description

Represents a fragment of a document used for semantic search.

### Responsibilities

- Store document fragments
- Reference vector embeddings
- Enable semantic retrieval

### Main Attributes

- id (UUID)
- documentId
- chunkIndex
- content
- embeddingId

---

## Chat

### Description

Represents a conversation between a user and EnterpriseAI.

### Main Attributes

- id (UUID)
- title
- owner
- createdAt

---

## Message

### Description

Represents a single message within a conversation.

### Main Attributes

- id (UUID)
- chatId
- sender
- content
- timestamp

---

## Entity Relationships

The initial relationships are defined as follows:

- A User can own multiple Chats.
- A Chat contains multiple Messages.
- A User can upload multiple Documents.
- A Document generates multiple Document Chunks.
- Each Document Chunk is indexed in Qdrant.
- A User can have one or more Roles.

---

## Future Entities

The following entities are planned for future releases:

- AuditLog
- Notification
- RefreshToken
- ApiKey
- SystemConfiguration

---

## Design Principles

The database model follows these principles:

- UUID as primary key
- Normalized relational model
- Referential integrity
- Audit fields
- Soft delete where applicable
- Designed for scalability