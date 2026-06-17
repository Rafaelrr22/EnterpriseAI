# Installation Guide

## Overview

This guide describes how to set up the EnterpriseAI development environment.

---

## Requirements

- Windows 11
- WSL2
- Docker Desktop
- Ollama
- Git
- IntelliJ IDEA

---

## Step 1 - Install WSL

Open PowerShell as Administrator.

```powershell
wsl --install
```

If WSL is already installed, update it:

```powershell
wsl --update
```

---

## Step 2 - Install Docker Desktop

Download Docker Desktop.

During installation:

- Enable WSL2
- Install for all users

Verify installation:

```powershell
docker --version
docker ps
```

---

## Step 3 - Install Ollama

Install Ollama.

Download the Qwen model:

```powershell
ollama pull qwen3
```

Verify:

```powershell
ollama run qwen3
```

---

## Step 4 - Install Open WebUI

Run:

```powershell
docker run -d ^
-p 3000:8080 ^
--add-host=host.docker.internal:host-gateway ^
-v open-webui:/app/backend/data ^
--name open-webui ^
--restart always ^
ghcr.io/open-webui/open-webui:main
```

Verify:

```powershell
docker ps
```

---

## Step 5 - Open WebUI

Open:

http://localhost:3000

Create the administrator account.

---

## Step 6 - Validate RAG

Upload a PDF.

Example questions:

- Who is responsible for the infrastructure?
- Where is production hosted?
- How are deployments performed?

Expected result:

The model should answer using the uploaded document as the information source.

---

## Status

✅ Installation completed successfully.