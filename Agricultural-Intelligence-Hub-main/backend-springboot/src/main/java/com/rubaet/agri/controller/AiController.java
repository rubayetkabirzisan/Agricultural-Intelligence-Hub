package com.rubaet.agri.controller;

import com.rubaet.agri.dto.AiRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.util.*;

@RestController
@RequestMapping("${app.api.base-path}/ai")
public class AiController {

    @Value("${app.ai.gemini-api-key}")
    private String apiKey;

    private static final String SYSTEM_INSTRUCTION =
        "You are AgriBot, a highly knowledgeable agricultural AI assistant. " +
        "You specialise in crop management, disease identification, soil science, " +
        "intercropping, weather impact on farming, and best agricultural practices " +
        "for South Asian and tropical climates. " +
        "Always give practical, actionable advice. Keep responses concise and farmer-friendly. " +
        "If asked something outside agriculture, gently redirect to farming topics.";

    private final RestTemplate restTemplate;

    public AiController() {
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/ask")
    public Mono<String> askAi(@RequestBody AiRequest request) {

        // Mock response if no real API key is configured
        if ("demo_key".equals(apiKey)) {
            String mockResponse = "{ \"candidates\": [ { \"content\": { \"parts\": [ { \"text\": \"" +
                "This is a mock AI response because a valid GEMINI_API_KEY was not provided. " +
                "To use the real AI, please set GEMINI_API_KEY before running the backend." +
                "\" } ] } } ] }";
            return Mono.just(mockResponse);
        }

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        // ── Build multi-turn Gemini API contents array ──────────────────────
        List<Map<String, Object>> contents = new ArrayList<>();

        // Add conversation history turns (alternating user / model)
        if (request.getHistory() != null && !request.getHistory().isEmpty()) {
            for (Map<String, String> turn : request.getHistory()) {
                String role = turn.getOrDefault("role", "user");
                String content = turn.getOrDefault("content", "");

                Map<String, Object> contentEntry = new HashMap<>();
                // Gemini uses "model" for assistant role
                contentEntry.put("role", "user".equals(role) ? "user" : "model");
                contentEntry.put("parts", List.of(Map.of("text", content)));
                contents.add(contentEntry);
            }
        }

        // Add the current user message as the final turn
        Map<String, Object> currentTurn = new HashMap<>();
        currentTurn.put("role", "user");
        currentTurn.put("parts", List.of(Map.of("text", request.getPrompt())));
        contents.add(currentTurn);

        // ── Build full request body with system instruction ──────────────────
        Map<String, Object> body = new HashMap<>();
        body.put("contents", contents);
        body.put("systemInstruction",
            Map.of("parts", List.of(Map.of("text", SYSTEM_INSTRUCTION))));

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            return Mono.just(response.getBody());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
}
