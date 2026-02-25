# Agricultural Intelligence Hub

This repository contains:

1) **JavaFX desktop application** (original project)
2) **Spring Boot backend REST API** (new) — built to demonstrate real-world Java backend skills (Spring Boot + JPA + MySQL + Swagger + layered architecture).

---

## ✅ Spring Boot Backend (Controller–Service–Repository)

Location: `backend-springboot/`

### Features
- Spring Boot 3 (Java 17)
- RESTful CRUD API
- JPA/Hibernate + MySQL
- Layered architecture (Controller → Service → Repository)
- Global exception handling (clean JSON errors)
- Swagger UI (OpenAPI) documentation

### Run locally (MySQL)
1. Start a MySQL server and create a database (optional — app can auto-create):
   - DB name: `agri_db`

2. Configure environment variables (optional; defaults exist):
   - `SPRING_DATASOURCE_URL`
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`

3. Run:
```bash
cd backend-springboot
mvn spring-boot:run
```

### API Docs
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

### Example endpoints
- `GET /api/crops`
- `POST /api/crops`
- `GET /api/crops/{id}`
- `PUT /api/crops/{id}`
- `DELETE /api/crops/{id}`

---

## Deploy on Render (Docker)

Render deployment uses the Dockerfile in `backend-springboot/`.

### Steps (high level)
1. Create a MySQL instance (Render guide) and note host/user/pass.
2. Create a new Render Web Service from this repo.
3. Set Root Directory: `backend-springboot`
4. Choose Environment: **Docker**
5. Add environment variables:
   - `SPRING_DATASOURCE_URL=jdbc:mysql://<HOST>:3306/agri_db`
   - `SPRING_DATASOURCE_USERNAME=<USER>`
   - `SPRING_DATASOURCE_PASSWORD=<PASS>`

After deploy:
- Swagger UI: `https://<your-service>.onrender.com/swagger-ui.html`

---

## Desktop App (JavaFX)

Original JavaFX application remains in the root Maven project.

