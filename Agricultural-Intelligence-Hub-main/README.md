# Agricultural Intelligence Hub 🌱

A comprehensive desktop application designed to help farmers and agricultural enthusiasts make data-driven decisions. Built with a modern Client-Server Architecture utilizing **JavaFX** for a stunning frontend and **Spring Boot** for a secure, robust backend.

## Features
- 🌱 **Crop Planning:** Recommends the best main crops and intercropping options based on the season and soil type.
- 🔬 **Diseases Identification:** Provides detailed information about common diseases, symptoms, and remedies for crops like Rice, Jute, Wheat, and Maize.
- ⛅ **Weather Integration:** Pulls a live 5-day weather forecast directly from OpenWeatherMap to help farmers plan their week.
- 🤖 **Ask AI Expert:** A virtual agricultural consultant powered by Google's Gemini AI. Ask questions about fertilizers, pest control, or crop cycles and get instant, intelligent answers.

## Architecture
- **Frontend:** JavaFX 21 + AtlantaFX (for modern theming)
- **Backend:** Spring Boot 3 + Spring WebFlux (for external API calls)
- **Database:** H2 Database (File-based, embedded)

## Getting Started

### Prerequisites
- JDK 21+
- Maven

### 1. Setup API Keys
For security reasons, API keys are not included in the repository. You must create your own `secrets.properties` file in the `backend-springboot` directory.

Create `backend-springboot/secrets.properties` and add your keys:
```properties
# OpenWeatherMap API Key
WEATHER_API_KEY=your_openweathermap_key_here

# Google Gemini API Key
GEMINI_API_KEY=your_gemini_key_here
```
*(Note: If you do not have keys, the application will fallback to demo mode and display mock data).*

### 2. Start the Backend Server
Open a terminal in the `backend-springboot` directory and run:
```bash
mvn spring-boot:run
```
The server will start on `http://localhost:8080`.

### 3. Start the JavaFX Client
Open a second terminal in the root project directory and run:
```bash
mvn clean javafx:run
```

## Security Note
This project is configured for local development. By default, the Spring Boot backend binds to `0.0.0.0:8080` without Spring Security. Do not deploy the backend directly to a public cloud server without adding authentication (e.g., Spring Security) to protect your API keys and the embedded H2 database console.

## License
MIT License
