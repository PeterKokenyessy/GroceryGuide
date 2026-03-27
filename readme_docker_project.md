# Fullstack Application (Spring Boot + Vite + PostgreSQL)

## 📦 Architecture Overview

This project runs as a multi-container Docker setup:

- **backend** → Spring Boot (Java)
- **frontend** → Vite (Node)
- **db** → PostgreSQL
- Docker Compose manages networking and volumes

All services run inside a shared Docker network.

---

# 🚀 Quick Start (Development Mode)

## 1️⃣ Build and start everything

```bash
docker compose up --build
```

This will:
- Build backend image
- Build frontend image
- Start PostgreSQL
- Create volumes if needed
- Start all containers

---

## 2️ Access the services

| Service    | URL                          |
|------------|-----------------------------|
| Frontend   | http://localhost:3000       |
| Backend    | http://localhost:8080       |
| Database   | localhost:5432              |

---

## 📦 Stop the project

```bash
docker compose down
```

Containers stop, but volumes remain.

---

# 🔁 When to Rebuild

You need to rebuild in the following cases:

### Rebuild Required

- You changed a Dockerfile
- You added/removed backend dependencies (pom.xml)
- You added/removed frontend dependencies (package.json)
- You changed base image version
- Something behaves strangely after structural changes

Rebuild command:

```bash
docker compose up --build
```

### No Rebuild Required

You **do NOT** need rebuild when:

- Editing Java source files (if using dev run mode)
- Editing frontend source files
- Changing environment variables in compose
- Adjusting controller/service logic

Just restart:

```bash
docker compose restart
```

---

# 👛 Clean Rebuild (Full Reset)

If things get weird, do a full reset:

```bash
docker compose down -v
docker compose build --no-cache
docker compose up
```

What this does:
- Removes containers
- Deletes volumes (database wiped)
- Rebuilds images from scratch

Use this only when necessary.

---

# 🛥 Database Access (pgAdmin / external client)

Use:

- Host: `localhost`
- Port: `5432`
- Database: `tesco_db`
- User: `postgres`
- Password: `postgres`

⚠️ If connection fails:
- Ensure no other PostgreSQL server is running on host
- Run `docker ps` and confirm port 5432 is mapped

---

# 🌐 Production Mode

To run in production mode:

```bash
docker compose -f docker-compose.yml -f docker-compose.prod.yml up --build -d
```

This:
- Applies production overrides
- Runs containers detached
- Uses production profile

To stop:

```bash
docker compose down
```

---

# 🛣 Volumes

Typical setup:

```yaml
volumes:
  - postgres_data:/var/lib/postgresql/data
```

### Important

- Volume persists database data
- `docker compose down` keeps volume
- `docker compose down -v` deletes volume

---

# 🔎 Useful Debug Commands

### View logs

```bash
docker compose logs
```

Specific service:

```bash
docker compose logs backend
docker compose logs frontend
docker compose logs db
```

Follow logs live:

```bash
docker compose logs -f backend
```

---

### Check running containers

```bash
docker ps
```

---

### Enter a container

Backend:

```bash
docker exec -it backend sh
```

Database:

```bash
docker exec -it db psql -U postgres
```

---

# 🔄 Common Workflow (Recommended)

### Day-to-day development

```bash
docker compose up
```

Edit code → auto reload

---

### After dependency changes

```bash
docker compose up --build
```

---

### After major structural changes

```bash
docker compose down -v
docker compose build --no-cache
docker compose up
```

---

# 🧠 Troubleshooting Guide

## Backend fails with schema validation error

Cause:
- Database volume empty
- Missing migration
- `ddl-auto=validate` but table not created

Fix options:
1. Switch to `update`
2. Add migration tool
3. Reset volume
4. Manually create table

---

## Frontend loads but cannot reach backend

Check:
- Backend is running
- Correct API base URL
- Correct Docker port mapping
- CORS configuration

---

## Port already in use

Find process:

```bash
lsof -i :5432
```

Or stop conflicting service.

---

# 📁 Project Structure

```
root/
│
├── backend/
│   ├── Dockerfile
│   └── src/
│
├── frontend/
│   ├── Dockerfile
│   └── src/
│
├── docker-compose.yml
├── docker-compose.prod.yml
└── README.md
```

---

# 🛠 Maintenance Best Practices

- Never expose DB port in production
- Keep `.env` out of version control
- Use healthchecks for service readiness
- Add migrations (Flyway/Liquibase)
- Add CI pipeline for image builds

---

# 🌟 Summary

Development:

```bash
docker compose up
```

Rebuild when needed:

```bash
docker compose up --build
```

Full reset:

```bash
docker compose down -v
docker compose build --no-cache
docker compose up
```

---

*Your dev stack is now structured like a real-world deployment.*

