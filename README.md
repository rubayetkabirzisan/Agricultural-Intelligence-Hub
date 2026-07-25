# Agricultural Intelligence Hub (Agri-Hub)

Agri-Hub is a comprehensive desktop platform designed to assist farmers with modern, data-driven agriculture. It combines a sleek, modern **JavaFX Desktop Application** with a secure and robust **Spring Boot Backend REST API**.

The application utilizes **Google Gemini AI** to provide personalized crop planning, disease identification, and expert advice, dynamically tailored to each farmer's specific profile (soil type, location, crops, and area).

---

## 🌟 Key Features

### Frontend (JavaFX)
- **Modern & Dynamic UI**: Features a premium design with a split-panel authentication screen, an animated 4-card dashboard, and seamless fade transitions between screens.
- **AI Assistant Hub**: Chat with AgriBot (powered by Gemini AI) for expert farming advice. Your farm profile is automatically injected into the AI context for personalized answers.
- **Yield & Financial Analytics**: Calculate estimated yield, gross revenue, costs, and net profit for your crops, visualized with interactive `BarChart` and `PieChart` components.
- **My Farm Profile**: Save your farm's identity (location, soil type, primary crops, size) which is seamlessly integrated into your workflow.
- **Weather Dashboard**: Access live weather forecasts tailored for your farm location.

### Backend (Spring Boot 3)
- **Robust Security Layer**:
  - API Key protection for service endpoints.
  - JWT Authentication for users (short-lived Access Tokens + 7-day Refresh Tokens).
  - Cross-Origin Resource Sharing (CORS) configured for localized security.
  - `@Valid` Request DTO validation across all endpoints.
- **AI Integration with Rate Limiting**: Features seamless integration with the Google Gemini API. Endpoints are protected by **Resilience4j Rate Limiting** to prevent quota exhaustion, complete with graceful fallbacks.
- **Automated Database Migrations**: Uses **Flyway** to handle schema migrations reliably (e.g., users table, farm profiles).
- **Embedded Database**: Uses an **H2 Database** for effortless setup—no external MySQL installation is required to get started.

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven (`mvnw` is included in the project)

### 1. Setup Environment Variables & Secrets
The backend relies on API keys for AI and Weather features. In the `backend-springboot` directory, create a `secrets.properties` file (you can copy `.env.example` from the project root as a starting point) and add your keys:

```properties
app.weather.api-key=YOUR_OPENWEATHER_API_KEY
app.ai.gemini-api-key=YOUR_GEMINI_API_KEY
app.jwt.secret=YOUR_LONG_RANDOM_SECRET_FOR_JWT
app.api-key=agri_hub_desktop_client_secret_2026
```
*(Note: `secrets.properties` is git-ignored for security.)*

### 2. Start the Backend Server
The Spring Boot backend must be running before you open the desktop app. It runs on `http://localhost:8080`.

```bash
cd backend-springboot
# Windows
..\mvnw.cmd spring-boot:run
# Mac/Linux
../mvnw spring-boot:run
```
*(Flyway will automatically create the schema and seed the database on startup.)*

### 3. Launch the JavaFX Frontend
In a new terminal window, return to the project root and start the desktop client:

```bash
# Windows
.\mvnw.cmd javafx:run
# Mac/Linux
./mvnw javafx:run
```

---

## 🏗️ Architecture

- **Backend**: Layered Architecture (Controller → Service → Repository). Built with Spring Boot 3, Spring Data JPA, Hibernate, and Resilience4j.
- **Frontend**: JavaFX using FXML for views. Navigation is handled centrally via a `SceneTransition` utility that provides smooth fade animations. Uses a global `AppState` singleton to manage authentication tokens and user context.

---

## 🛠️ Security Hardening
- Secrets are externalized outside of application code.
- Passwords are encrypted in the database.
- Database console (H2) is locked to the dev profile only.
- Strict `@Valid` input validation on all DTOs and Controller parameters.
- Dual-filter chain architecture (`ApiKeyAuthFilter` + `JwtAuthFilter`) securing different scopes of the API.

---

*v2.0 — Powered by Google Gemini AI*
