package com.example.demo4.service;

import com.example.demo4.state.AppState;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Centralized async HTTP service for the Spring Boot backend.
 * All methods return CompletableFuture and are non-blocking.
 */
public class ApiService {

    private static final String API_KEY  = "agri_hub_desktop_client_secret_2026";
    private static final String BASE_URL = "http://localhost:8080/api";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // ── Auth ──────────────────────────────────────────────────────

    /**
     * Login. Returns String[2] { accessToken, refreshToken } or null on failure.
     */
    public static CompletableFuture<String[]> login(String email, String password) {
        return authRequest("/auth/login", email, password);
    }

    /**
     * Register. Returns String[2] { accessToken, refreshToken } or null on failure.
     */
    public static CompletableFuture<String[]> register(String email, String password) {
        return authRequest("/auth/register", email, password);
    }

    private static CompletableFuture<String[]> authRequest(String endpoint, String email, String password) {
        String json = "{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}";
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("X-API-KEY", API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return CLIENT.sendAsync(req, HttpResponse.BodyHandlers.ofString()).thenApply(res -> {
            if (res.statusCode() == 200) {
                try {
                    JsonNode root = MAPPER.readTree(res.body());
                    String token   = root.path("token").asText(null);
                    String refresh = root.path("refreshToken").asText(null);
                    return new String[]{token, refresh};
                } catch (Exception ignored) {}
            }
            return null;
        });
    }

    // ── AI ────────────────────────────────────────────────────────

    public static CompletableFuture<String> askAi(String prompt, List<Map<String, String>> history) {
        return sendAiPost("/ai/ask", prompt, history);
    }

    public static CompletableFuture<String> askAiCropPlan(String userInput, String ignored) {
        return sendAiPost("/ai/plan-crop", userInput, null);
    }

    public static CompletableFuture<String> askAiDisease(String symptoms) {
        return sendAiPost("/ai/identify-disease", "Symptoms: " + symptoms, null);
    }

    private static CompletableFuture<String> sendAiPost(String endpoint, String prompt,
                                                         List<Map<String, String>> history) {
        try {
            Map<String, Object> body = history != null && !history.isEmpty()
                ? Map.of("prompt", prompt, "history", history)
                : Map.of("prompt", prompt);

            String json = MAPPER.writeValueAsString(body);
            HttpRequest.Builder rb = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .header("X-API-KEY", API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json));
            addJwt(rb);
            return CLIENT.sendAsync(rb.build(), HttpResponse.BodyHandlers.ofString())
                         .thenApply(HttpResponse::body);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    // ── Weather ───────────────────────────────────────────────────

    public static CompletableFuture<String> getWeatherData(String city) {
        HttpRequest.Builder rb = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/weather?city=" + city.replace(" ", "%20")))
                .header("X-API-KEY", API_KEY)
                .GET();
        addJwt(rb);
        return CLIENT.sendAsync(rb.build(), HttpResponse.BodyHandlers.ofString())
                     .thenApply(HttpResponse::body);
    }

    // ── Crop Data ─────────────────────────────────────────────────

    public static CompletableFuture<String> getCropData(String cropName) {
        String name = cropName.endsWith(" Summary") ? cropName : cropName + " Summary";
        HttpRequest.Builder rb = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/crops/search?name=" + name.replace(" ", "%20")))
                .header("X-API-KEY", API_KEY)
                .GET();
        addJwt(rb);
        return CLIENT.sendAsync(rb.build(), HttpResponse.BodyHandlers.ofString())
                     .thenApply(HttpResponse::body);
    }

    // ── Farm Profile ──────────────────────────────────────────────

    public static CompletableFuture<String> getFarmProfile() {
        HttpRequest.Builder rb = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/profile/me"))
                .header("X-API-KEY", API_KEY)
                .GET();
        addJwt(rb);
        return CLIENT.sendAsync(rb.build(), HttpResponse.BodyHandlers.ofString())
                     .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> saveFarmProfile(
            String farmName, String location, String soilType,
            double areaHectares, String primaryCrops, String region) {
        try {
            Map<String, Object> body = Map.of(
                "farmName", farmName,
                "location", location,
                "soilType", soilType,
                "areaHectares", areaHectares,
                "primaryCrops", primaryCrops,
                "region", region
            );
            String json = MAPPER.writeValueAsString(body);
            HttpRequest.Builder rb = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/profile"))
                    .header("X-API-KEY", API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json));
            addJwt(rb);
            return CLIENT.sendAsync(rb.build(), HttpResponse.BodyHandlers.ofString())
                         .thenApply(HttpResponse::body);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    // ── Analytics ─────────────────────────────────────────────────

    public static CompletableFuture<String> getYieldEstimate(String crop, double areaHectares, double seasonFactor) {
        try {
            Map<String, Object> body = Map.of(
                "crop", crop,
                "areaHectares", areaHectares,
                "seasonYieldFactor", seasonFactor
            );
            String json = MAPPER.writeValueAsString(body);
            HttpRequest.Builder rb = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/analytics/yield-estimate"))
                    .header("X-API-KEY", API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json));
            addJwt(rb);
            return CLIENT.sendAsync(rb.build(), HttpResponse.BodyHandlers.ofString())
                         .thenApply(HttpResponse::body);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    // ── Helpers ───────────────────────────────────────────────────

    private static void addJwt(HttpRequest.Builder rb) {
        String token = AppState.getInstance().getJwtToken();
        if (token != null && !token.isEmpty()) {
            rb.header("Authorization", "Bearer " + token);
        }
    }
}
