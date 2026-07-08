package com.rubaet.agri.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${app.api.base-path}/weather")
public class WeatherController {

    @Value("${app.weather.api-key}")
    private String apiKey;

    @Value("${app.weather.base-url}")
    private String baseUrl;

    private final WebClient webClient;

    public WeatherController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @GetMapping
    public Mono<String> getWeather(@RequestParam String city) {
        if ("demo_key".equals(apiKey)) {
            try {
                String mockData = java.nio.file.Files.readString(java.nio.file.Paths.get("../next30days.json"));
                return Mono.just(mockData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String url = baseUrl + "/forecast?q=" + city + "&cnt=50&appid=" + apiKey;
        
        return this.webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }
}
