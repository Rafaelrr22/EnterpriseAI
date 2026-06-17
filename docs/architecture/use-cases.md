# Use Cases

## Overview

This document describes the primary use cases supported by EnterpriseAI.

Each use case represents a business capability that will later be implemented through the REST API and exposed to the Angular frontend.

---

# Actors

The following actors interact with the system:

- Guest
- Authenticated User
- Administrator
- EnterpriseAI (AI Service)

---

# UC-001 - User Authentication

## Description

Allows a registered user to authenticate and access the platform.

### Actor

Authenticated User

### Preconditions

- User account exists
- User account is enabled

### Main Flow

1. User enters credentials.
2. The system validates the credentials.
3. A JWT token is generated.
4. The user gains access to the platform.

### Alternative Flow

- Invalid credentials
- Disabled account

---

# UC-002 - Upload Document

## Description

Allows a user to upload documents to the knowledge base.

### Actor

Authenticated User

### Preconditions

- User is authenticated.

### Main Flow

1. User selects a document.
2. The document is uploaded.
3. Metadata is extracted.
4. The document is stored.
5. The document is queued for indexing.

### Supported Formats

- PDF
- DOCX
- TXT
- Markdown

---

# UC-003 - Ask AI a Question

## Description

Allows a user to ask questions using natural language.

### Actor

Authenticated User

### Preconditions

- User is authenticated.
- Knowledge base is available.

### Main Flow

1. User submits a question.
2. EnterpriseAI searches the knowledge base.
3. Relevant document chunks are retrieved.
4. Context is sent to Ollama.
5. The AI generates a response.
6. The response is displayed to the user.

---

# UC-004 - Search Documents

## Description

Allows users to search uploaded documents.

### Actor

Authenticated User

### Main Flow

1. User enters search criteria.
2. The system searches indexed documents.
3. Matching documents are returned.

---

# UC-005 - View Chat History

## Description

Allows users to access previous conversations.

### Actor

Authenticated User

### Main Flow

1. User opens chat history.
2. Existing conversations are displayed.
3. User selects a conversation.
4. Messages are loaded.

---

# UC-006 - Manage Users

## Description

Allows administrators to manage platform users.

### Actor

Administrator

### Main Flow

1. Administrator views users.
2. Administrator creates or edits users.
3. Administrator assigns roles.
4. Administrator enables or disables accounts.

---

# UC-007 - Manage Documents

## Description

Allows administrators to manage uploaded documents.

### Actor

Administrator

### Main Flow

1. View uploaded documents.
2. Delete documents.
3. Update document metadata.
4. Reindex documents.

---

# UC-008 - Manage AI Configuration

## Description

Allows administrators to configure the AI environment.

### Actor

Administrator

### Main Flow

1. Configure Ollama connection.
2. Select AI model.
3. Configure embedding model.
4. Configure vector database.

---

# Future Use Cases

Future versions may include:

- Microsoft Entra ID Login
- LDAP Authentication
- API Key Management
- Notifications
- Audit Logs
- Multi-Tenant Support