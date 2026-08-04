package com.rubaet.agri.controller;

import com.rubaet.agri.entity.FarmProfile;
import com.rubaet.agri.entity.HarvestRecord;
import com.rubaet.agri.entity.User;
import com.rubaet.agri.repository.FarmProfileRepository;
import com.rubaet.agri.repository.HarvestRecordRepository;
import com.rubaet.agri.repository.UserRepository;
import com.rubaet.agri.service.MarketPriceService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Generates a farm summary report as a downloadable plain-text document.
 * Uses plain text formatting to avoid external PDF library dependencies.
 * Can be upgraded to iText/PDFBox later for richer formatting.
 */
@RestController
@RequestMapping("${app.api.base-path}/reports")
public class ReportController {

    private final UserRepository userRepo;
    private final FarmProfileRepository profileRepo;
    private final HarvestRecordRepository harvestRepo;
    private final MarketPriceService priceService;

    public ReportController(UserRepository userRepo, FarmProfileRepository profileRepo,
                            HarvestRecordRepository harvestRepo, MarketPriceService priceService) {
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
        this.harvestRepo = harvestRepo;
        this.priceService = priceService;
    }

    @GetMapping("/farm-summary")
    public ResponseEntity<?> generateReport(Authentication auth) {
        User user = resolveUser(auth);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));

        FarmProfile profile = profileRepo.findByUserId(user.getId()).orElse(null);
        List<HarvestRecord> harvests = harvestRepo.findByUserIdOrderByYearDescSeasonDesc(user.getId());

        String report = buildReport(user, profile, harvests);
        byte[] bytes = report.getBytes(StandardCharsets.UTF_8);

        String filename = "AgriHub_Farm_Report_" + DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.systemDefault()).format(Instant.now()) + ".txt";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", filename);
        headers.setContentLength(bytes.length);

        return ResponseEntity.ok().headers(headers).body(bytes);
    }

    private String buildReport(User user, FarmProfile profile, List<HarvestRecord> harvests) {
        StringBuilder sb = new StringBuilder();
        String line = "═".repeat(60);
        String thin = "─".repeat(60);

        sb.append(line).append("\n");
        sb.append("       AGRICULTURAL INTELLIGENCE HUB — FARM REPORT\n");
        sb.append(line).append("\n\n");

        sb.append("Generated: ").append(Instant.now()).append("\n");
        sb.append("User: ").append(user.getEmail()).append("\n\n");

        // Farm Profile
        sb.append(thin).append("\n");
        sb.append("  📍 FARM PROFILE\n");
        sb.append(thin).append("\n");
        if (profile != null) {
            sb.append("  Farm Name:     ").append(safe(profile.getFarmName())).append("\n");
            sb.append("  Location:      ").append(safe(profile.getLocation())).append("\n");
            sb.append("  Region:        ").append(safe(profile.getRegion())).append("\n");
            sb.append("  Soil Type:     ").append(safe(profile.getSoilType())).append("\n");
            sb.append("  Area:          ").append(profile.getAreaHectares()).append(" hectares\n");
            sb.append("  Primary Crops: ").append(safe(profile.getPrimaryCrops())).append("\n");
        } else {
            sb.append("  (No farm profile set up yet)\n");
        }
        sb.append("\n");

        // Harvest History
        sb.append(thin).append("\n");
        sb.append("  📊 HARVEST HISTORY (").append(harvests.size()).append(" records)\n");
        sb.append(thin).append("\n");
        if (harvests.isEmpty()) {
            sb.append("  (No harvest records yet)\n");
        } else {
            sb.append(String.format("  %-15s %-10s %-6s %12s %12s\n", "Crop", "Season", "Year", "Yield (kg)", "Revenue ($)"));
            sb.append("  ").append("─".repeat(57)).append("\n");
            double totalRevenue = 0;
            double totalYield = 0;
            for (HarvestRecord h : harvests) {
                sb.append(String.format("  %-15s %-10s %-6d %,12.0f %,12.2f\n",
                    h.getCropName(), safe(h.getSeason()), h.getYear(), h.getActualYieldKg(), h.getRevenueUsd()));
                totalRevenue += h.getRevenueUsd();
                totalYield += h.getActualYieldKg();
            }
            sb.append("  ").append("─".repeat(57)).append("\n");
            sb.append(String.format("  %-33s %,12.0f %,12.2f\n", "TOTAL", totalYield, totalRevenue));
        }
        sb.append("\n");

        // Footer
        sb.append(line).append("\n");
        sb.append("  Powered by Agri-Hub v2.0 — Agricultural Intelligence Hub\n");
        sb.append(line).append("\n");

        return sb.toString();
    }

    private String safe(String s) { return s != null ? s : "—"; }

    private User resolveUser(Authentication auth) {
        if (auth == null) return null;
        return userRepo.findByEmail(auth.getName()).orElse(null);
    }
}
