package com.rubaet.agri.controller;

import com.rubaet.agri.dto.CropCreateRequest;
import com.rubaet.agri.dto.CropResponse;
import com.rubaet.agri.dto.CropUpdateRequest;
import com.rubaet.agri.service.CropService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${app.api.base-path:/api}/crops")
public class CropController {

    private final CropService service;

    public CropController(CropService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CropResponse> create(@Valid @RequestBody CropCreateRequest req) {
        CropResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create(String.format("%s/crops/%d", "/api", created.getId())))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<CropResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CropResponse> update(@PathVariable Long id, @Valid @RequestBody CropUpdateRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
