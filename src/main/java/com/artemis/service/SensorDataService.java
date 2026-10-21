package com.artemis.service;

import com.artemis.dto.SensorReadingRequest;
import com.artemis.dto.SensorReadingResponse;
import com.artemis.entity.SensorData;
import com.artemis.repository.SensorDataRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SensorDataService {

    private static final Logger logger = LoggerFactory.getLogger(SensorDataService.class);
    private final SensorDataRepository repository;
    private final EntityManager entityManager;

    // Constructor Injection
    public SensorDataService(SensorDataRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    /**
     * Ingests a new sensor reading.
     * Triggers a warning log if values are out of expected ranges.
     */
    public SensorReadingResponse ingestReading(SensorReadingRequest request) {
        // Map DTO to Entity
        SensorData data = new SensorData();
        data.setAcousticPing(request.getAcousticPing());
        data.setTemperature(request.getTemperature());
        data.setSalinity(request.getSalinity());
        data.setTimestamp(request.getTimestamp());
        data.setSourceDeviceId(request.getSourceDeviceId());
        
        // Pre-analysis: Check for anomalies
        if (request.getTemperature() < -2.0 || request.getTemperature() > 40.0) {
            logger.warn("Anomaly detected: Temperature out of range ({}) for device {}", 
                        request.getTemperature(), request.getSourceDeviceId());
        }
        if (request.getSalinity() < 0.0 || request.getSalinity() > 40.0) {
            logger.warn("Anomaly detected: Salinity out of range ({}) for device {}", 
                        request.getSalinity(), request.getSourceDeviceId());
        }

        SensorData saved = repository.save(data);
        
        // Map back to Response
        SensorReadingResponse response = new SensorReadingResponse();
        response.setId(saved.getId());
        response.setTemperature(saved.getTemperature());
        response.setSalinity(saved.getSalinity());
        response.setAcousticPing(saved.getAcousticPing());
        response.setTimestamp(saved.getTimestamp());
        response.setSourceDeviceId(saved.getSourceDeviceId());
        // Pre-analysis flag mirrors the ingest-time warnings; the AI layer refines it later
        boolean anomaly = (saved.getTemperature() < -2.0f || saved.getTemperature() > 40.0f)
                || (saved.getSalinity() < 0.0f || saved.getSalinity() > 40.0f);
        response.setAnomalySuspected(anomaly);
        
        return response;
    }

    /**
     * Returns the revision numbers recorded by Envers for a given sensor record.
     */
    public List<Long> getAuditHistory(Long id) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        List<Number> revisions = reader.getRevisions(SensorData.class, id);
        return revisions.stream().map(Number::longValue).collect(Collectors.toList());
    }

    /**
     * Fetches recent data for AI analysis (most recent first).
     */
    public List<SensorReadingResponse> getRecentReadings(int limit) {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusDays(365);
        List<SensorData> readings = repository.findByTimestampBetweenOrderByTimestampDesc(start, end);
        return readings.stream()
                .limit(limit)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private SensorReadingResponse mapToResponse(SensorData data) {
        SensorReadingResponse response = new SensorReadingResponse();
        response.setId(data.getId());
        response.setTemperature(data.getTemperature());
        response.setSalinity(data.getSalinity());
        response.setAcousticPing(data.getAcousticPing());
        response.setTimestamp(data.getTimestamp());
        response.setSourceDeviceId(data.getSourceDeviceId());
        return response;
    }
}