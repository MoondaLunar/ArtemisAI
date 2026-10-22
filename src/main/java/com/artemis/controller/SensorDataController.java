package com.artemis.controller;

import com.artemis.dto.SensorReadingRequest;
import com.artemis.dto.SensorReadingResponse;
import com.artemis.service.SensorDataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST surface for sensor ingestion and audit queries.
 * Previously the service existed but nothing called it.
 */
@RestController
@RequestMapping("/api/sensors")
public class SensorDataController {

    private final SensorDataService service;

    public SensorDataController(SensorDataService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SensorReadingResponse> ingest(@Valid @RequestBody SensorReadingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.ingestReading(request));
    }

    @GetMapping("/recent")
    public List<SensorReadingResponse> recent(@RequestParam(defaultValue = "20") int limit) {
        return service.getRecentReadings(limit);
    }

    @GetMapping("/{id}/audit")
    public List<Long> auditHistory(@PathVariable Long id) {
        return service.getAuditHistory(id);
    }
}
