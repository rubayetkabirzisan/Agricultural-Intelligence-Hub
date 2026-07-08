package com.rubaet.agri.controller;

import com.rubaet.agri.dto.AiRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("${app.api.base-path}/ai")
public class AiController {

    @Value("${app.ai.gemini-api-key}")
    private String apiKey;

    private final WebClient webClient;

    public AiController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://generativelanguage.googleapis.com").build();
    }

    @PostMapping("/ask")
    public Mono<String> askAi(@RequestBody AiRequest request) {
        if ("demo_key".equals(apiKey)) {
            String mockResponse = "{ \"candidates\": [ { \"content\": { \"parts\": [ { \"text\": \"This is a mock AI response because a valid GEMINI_API_KEY was not provided in the environment. To use the real AI, please export GEMINI_API_KEY before running the backend.\" } ] } } ] }";
            return Mono.just(mockResponse);
        }

        String url = "/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;
        
        // Build the Gemini API Request Body dynamically
        Map<String, Object> body = new HashMap<>();
        Map<String, Object> contents = new HashMap<>();
        Map<String, Object> parts = new HashMap<>();
        
        parts.put("text", "You are an agricultural expert AI. " + request.getPrompt());
        contents.put("parts", new Object[]{parts});
        body.put("contents", new Object[]{contents});

        return this.webClient.post()
                .uri(url)
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }
}
