package com.rubaet.agri.controller;

import com.rubaet.agri.service.MarketPriceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("${app.api.base-path}/analytics")
public class AnalyticsController {

    private final MarketPriceService marketPriceService;

    public AnalyticsController(MarketPriceService marketPriceService) {
        this.marketPriceService = marketPriceService;
    }

    /**
     * POST /api/analytics/yield-estimate
     * Returns estimated yield, revenue, cost, and profit for a given crop and area.
     */
    @PostMapping("/yield-estimate")
    public ResponseEntity<Map<String, Object>> estimate(@Valid @RequestBody YieldRequest request) {
        String crop = request.getCrop().trim();
        double hectares = request.getAreaHectares();
        double season = request.getSeasonYieldFactor(); // 0.7–1.3 based on season

        double yieldPerHa = marketPriceService.getYieldPerHectare(crop);
        double pricePerKg = marketPriceService.getPricePerKg(crop);

        double totalYieldKg = yieldPerHa * hectares * season;
        double grossRevenue = totalYieldKg * pricePerKg;

        // Estimated cost: seed + fertilizer + labor ≈ 35% of gross revenue (avg South Asia)
        double estimatedCost = grossRevenue * 0.35;
        double netProfit = grossRevenue - estimatedCost;
        double profitMargin = (netProfit / grossRevenue) * 100;

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("crop", crop);
        result.put("areaHectares", hectares);
        result.put("estimatedYieldKg", Math.round(totalYieldKg));
        result.put("pricePerKgUSD", pricePerKg);
        result.put("grossRevenueUSD", Math.round(grossRevenue * 100.0) / 100.0);
        result.put("estimatedCostUSD", Math.round(estimatedCost * 100.0) / 100.0);
        result.put("netProfitUSD", Math.round(netProfit * 100.0) / 100.0);
        result.put("profitMarginPercent", Math.round(profitMargin * 10.0) / 10.0);
        result.put("priceSource", "2024 Estimated Market Prices (South Asia Average)");
        result.put("cropKnown", marketPriceService.cropIsKnown(crop));

        return ResponseEntity.ok(result);
    }
}

// ── Request DTO ──────────────────────────────────────────────────
class YieldRequest {
    @NotBlank(message = "Crop name is required")
    private String crop;

    @DecimalMin(value = "0.01", message = "Area must be at least 0.01 hectares")
    private double areaHectares;

    /** 1.0 = average season, 0.7 = bad season (drought), 1.3 = great season */
    private double seasonYieldFactor = 1.0;

    public String getCrop() { return crop; }
    public void setCrop(String crop) { this.crop = crop; }
    public double getAreaHectares() { return areaHectares; }
    public void setAreaHectares(double areaHectares) { this.areaHectares = areaHectares; }
    public double getSeasonYieldFactor() { return seasonYieldFactor; }
    public void setSeasonYieldFactor(double seasonYieldFactor) { this.seasonYieldFactor = seasonYieldFactor; }
}
