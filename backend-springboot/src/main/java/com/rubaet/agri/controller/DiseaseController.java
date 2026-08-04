package com.rubaet.agri.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${app.api.base-path:/api}/diseases")
public class DiseaseController {

    @GetMapping("/{cropName}")
    public ResponseEntity<List<Map<String, String>>> getDiseasesForCrop(@PathVariable String cropName) {
        if ("Rice".equalsIgnoreCase(cropName)) {
            return ResponseEntity.ok(List.of(
                    Map.of("name", "Bacterial Blight", "description", "Water-soaked to yellowish stripes on leaf blades or starting at leaf tips then later increase in length and width with a wavy margin."),
                    Map.of("name", "Bacterial Leaf Streak", "description", "Initial symptoms are small, water-soaked, linear lesions between leaf veins. These lesions enlarge, turn yellowish-brown, and may coalesce."),
                    Map.of("name", "Blast (Leaf and Collar)", "description", "Spindle-shaped spots with gray center and brownish margins. In severe infections, lesions coalesce and leaves may dry up."),
                    Map.of("name", "Blast (Node and Neck)", "description", "Infection at the panicle base causes 'neck rot' or 'rotten neck'. The infected neck becomes grayish brown, causing the panicle to fall over.")
            ));
        } else if ("Jute".equalsIgnoreCase(cropName)) {
            return ResponseEntity.ok(List.of(
                    Map.of("name", "Jute Anthracnose", "description", "Small, dark, depressed lesions on stems, leaves, and pods. Infected tissues may dry up and fall off."),
                    Map.of("name", "Jute Red Rot", "description", "Reddening of the stem, often starting near the base. Leaves may turn yellow and wilt."),
                    Map.of("name", "Jute Stem Rot", "description", "Dark brown lesions on the stem, leading to rotting and eventual death of the plant."),
                    Map.of("name", "Jute Yellow Mosaic", "description", "Yellow mosaic patterns on leaves, stunted growth, and reduced yield.")
            ));
        } else if ("Wheat".equalsIgnoreCase(cropName)) {
            return ResponseEntity.ok(List.of(
                    Map.of("name", "Wheat Rust", "description", "Orange, yellow, or brown pustules on leaves and stems."),
                    Map.of("name", "Powdery Mildew", "description", "White, powdery fungal growth on the upper surface of leaves."),
                    Map.of("name", "Fusarium Head Blight", "description", "Bleaching of spikelets, often with a pinkish or salmon-colored fungal growth."),
                    Map.of("name", "Septoria Leaf Blotch", "description", "Irregular, necrotic lesions on leaves, often with small black fruiting bodies.")
            ));
        } else if ("Maize".equalsIgnoreCase(cropName)) {
            return ResponseEntity.ok(List.of(
                    Map.of("name", "Corn Smut", "description", "Large, gall-like tumors on ears, tassels, stalks, and leaves."),
                    Map.of("name", "Northern Corn Leaf Blight", "description", "Large, cigar-shaped, grayish-green to tan lesions on leaves."),
                    Map.of("name", "Southern Corn Leaf Blight", "description", "Smaller, tan lesions with reddish-brown borders on leaves."),
                    Map.of("name", "Stalk Rot", "description", "Decay of the inner stalk tissue, leading to lodging and premature death.")
            ));
        }
        
        return ResponseEntity.notFound().build();
    }
}
