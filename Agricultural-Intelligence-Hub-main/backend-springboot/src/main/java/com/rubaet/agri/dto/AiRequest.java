package com.rubaet.agri.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

public class AiRequest {
    
    @NotBlank(message = "Prompt is required")
    private String prompt;

    /**
     * Optional conversation history for multi-turn memory.
     * Each entry is a map with keys "role" ("user" or "model") and "content" (message text).
     * The backend will include these as prior turns in the Gemini API request.
     */
    private List<Map<String, String>> history;

    // Getters and Setters
    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public List<Map<String, String>> getHistory() { return history; }
    public void setHistory(List<Map<String, String>> history) { this.history = history; }
}
