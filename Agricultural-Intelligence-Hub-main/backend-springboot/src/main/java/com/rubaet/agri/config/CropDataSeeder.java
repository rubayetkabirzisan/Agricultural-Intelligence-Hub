package com.rubaet.agri.config;

import com.rubaet.agri.entity.Crop;
import com.rubaet.agri.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class CropDataSeeder implements CommandLineRunner {

    @Autowired
    private CropRepository cropRepository;

    @Override
    public void run(String... args) throws Exception {
        if (cropRepository.count() > 0) {
            System.out.println("Crops already seeded.");
            return;
        }

        System.out.println("Seeding crops from text files...");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        
        try {
            Resource[] resources = resolver.getResources("classpath:data/CSVFILES/ZishanFiles/ZishanCSVFILES/*.txt");
            for (Resource resource : resources) {
                String filename = resource.getFilename();
                if (filename != null) {
                    String name = filename.replace(".txt", "").trim();
                    try (InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
                        String content = FileCopyUtils.copyToString(reader);
                        
                        Crop crop = new Crop();
                        crop.setName(name);
                        crop.setSeason("All Seasons"); // Default
                        crop.setDescription(content.trim());
                        
                        try {
                            cropRepository.save(crop);
                        } catch (Exception e) {
                            System.err.println("Skipped duplicate or invalid crop: " + name);
                        }
                    }
                }
            }
            
            // Also seed Summary info if needed
            Resource[] summaryResources = resolver.getResources("classpath:data/CSVFILES/ZishanFiles/Summary info/*.txt");
            for (Resource resource : summaryResources) {
                String filename = resource.getFilename();
                if (filename != null) {
                    String name = filename.replace(".txt", "").replace("summary", "").replace("Summary", "").trim();
                    try (InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
                        String content = FileCopyUtils.copyToString(reader);
                        
                        Crop crop = new Crop();
                        crop.setName(name + " Summary");
                        crop.setSeason("Summary");
                        crop.setDescription(content.trim());
                        
                        try {
                            cropRepository.save(crop);
                        } catch (Exception e) {
                            System.err.println("Skipped duplicate summary: " + name);
                        }
                    }
                }
            }
            System.out.println("Crop data successfully seeded!");
        } catch (Exception e) {
            System.err.println("Failed to read crop data resources: " + e.getMessage());
        }
    }
}
