package com.rubaet.agri.dto;

import jakarta.validation.constraints.NotBlank;

public class AiRequest {
    
    @NotBlank(message = "Prompt is required")
    private String prompt;

    // Getters and Setters
    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
}
