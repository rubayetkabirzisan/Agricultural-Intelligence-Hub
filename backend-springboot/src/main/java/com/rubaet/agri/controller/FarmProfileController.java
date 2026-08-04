package com.rubaet.agri.controller;

import com.rubaet.agri.entity.FarmProfile;
import com.rubaet.agri.entity.User;
import com.rubaet.agri.repository.FarmProfileRepository;
import com.rubaet.agri.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.api.base-path}/profile")
public class FarmProfileController {

    private final FarmProfileRepository farmProfileRepository;
    private final UserRepository userRepository;

    public FarmProfileController(FarmProfileRepository farmProfileRepository, UserRepository userRepository) {
        this.farmProfileRepository = farmProfileRepository;
        this.userRepository = userRepository;
    }

    /** Returns the current user's farm profile (null fields if not set yet). */
    @GetMapping("/me")
    public ResponseEntity<?> getProfile(Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body("{\"error\": \"Unauthorized\"}");
        FarmProfile profile = farmProfileRepository.findByUserId(user.getId()).orElse(new FarmProfile());
        return ResponseEntity.ok(profile);
    }

    /** Creates or updates the current user's farm profile. */
    @PostMapping
    public ResponseEntity<?> saveProfile(@Valid @RequestBody FarmProfileRequest request, Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body("{\"error\": \"Unauthorized\"}");

        FarmProfile profile = farmProfileRepository.findByUserId(user.getId()).orElse(new FarmProfile());
        profile.setUserId(user.getId());
        profile.setFarmName(request.getFarmName());
        profile.setLocation(request.getLocation());
        profile.setSoilType(request.getSoilType());
        profile.setAreaHectares(request.getAreaHectares());
        profile.setPrimaryCrops(request.getPrimaryCrops());
        profile.setRegion(request.getRegion());

        farmProfileRepository.save(profile);
        return ResponseEntity.ok(profile);
    }

    private User resolveUser(Authentication auth) {
        if (auth == null) return null;
        return userRepository.findByEmail(auth.getName()).orElse(null);
    }
}

// ── Request DTO ──────────────────────────────────────────────────
class FarmProfileRequest {
    @Size(max = 120) private String farmName;
    @Size(max = 200) private String location;
    @Size(max = 80)  private String soilType;
    private Double areaHectares;
    @Size(max = 300) private String primaryCrops;
    @Size(max = 100) private String region;

    public String getFarmName() { return farmName; }
    public void setFarmName(String farmName) { this.farmName = farmName; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }
    public Double getAreaHectares() { return areaHectares; }
    public void setAreaHectares(Double areaHectares) { this.areaHectares = areaHectares; }
    public String getPrimaryCrops() { return primaryCrops; }
    public void setPrimaryCrops(String primaryCrops) { this.primaryCrops = primaryCrops; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
}
