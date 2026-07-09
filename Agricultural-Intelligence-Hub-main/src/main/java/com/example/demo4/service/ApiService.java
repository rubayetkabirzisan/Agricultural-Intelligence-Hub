package com.example.demo4.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import com.example.demo4.state.AppState;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Centralized service for making asynchronous HTTP requests to the Spring Boot backend.
 */
public class ApiService {

    private static final String API_KEY = "agri_hub_desktop_client_secret_2026";
    private static final String BASE_URL = "http://localhost:8080/api";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static CompletableFuture<String> getCropData(String cropName) {
        String searchName = cropName.endsWith(" Summary") ? cropName : cropName + " Summary";
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/crops/search?name=" + searchName.replace(" ", "%20")))
                .header("X-API-KEY", API_KEY)
                .GET();
        addJwtHeader(requestBuilder);

        return client.sendAsync(requestBuilder.build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> getDiseaseData(String cropName) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/diseases/" + cropName.replace(" ", "%20")))
                .header("X-API-KEY", API_KEY)
                .GET();
        addJwtHeader(requestBuilder);

        return client.sendAsync(requestBuilder.build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> getWeatherData(String city) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/weather?city=" + city.replace(" ", "%20")))
                .header("X-API-KEY", API_KEY)
                .GET();
        addJwtHeader(requestBuilder);

        return client.sendAsync(requestBuilder.build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> askAi(String prompt, java.util.List<java.util.Map<String, String>> history) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            java.util.Map<String, Object> requestBody = new java.util.HashMap<>();
            requestBody.put("prompt", prompt);
            if (history != null && !history.isEmpty()) {
                requestBody.put("history", history);
            }
            String jsonPayload = mapper.writeValueAsString(requestBody);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/ai/ask"))
                    .header("X-API-KEY", API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload));
            addJwtHeader(requestBuilder);

            return client.sendAsync(requestBuilder.build(), HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body);
        } catch (Exception e) {
            return java.util.concurrent.CompletableFuture.failedFuture(e);
        }
    }

    public static CompletableFuture<String> login(String email, String password) {
        return authenticate("/auth/login", email, password);
    }

    public static CompletableFuture<String> register(String email, String password) {
        return authenticate("/auth/register", email, password);
    }

    private static CompletableFuture<String> authenticate(String endpoint, String email, String password) {
        String jsonPayload = "{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("X-API-KEY", API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    if (response.statusCode() == 200) {
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            JsonNode json = mapper.readTree(response.body());
                            return json.get("token").asText();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                });
    }

    private static void addJwtHeader(HttpRequest.Builder requestBuilder) {
        String token = AppState.getInstance().getJwtToken();
        if (token != null && !token.isEmpty()) {
            requestBuilder.header("Authorization", "Bearer " + token);
        }
    }
}
