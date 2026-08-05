package com.rubaet.agri.dto;

import jakarta.validation.constraints.Size;

public class CropUpdateRequest {

    @Size(max = 120, message = "name must be at most 120 characters")
    private String name;

    @Size(max = 60, message = "season must be at most 60 characters")
    private String season;

    @Size(max = 2000, message = "description must be at most 2000 characters")
    private String description;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
