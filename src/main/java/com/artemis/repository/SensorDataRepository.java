package com.artemis.repository;

import com.artemis.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long>, RevisionRepository<SensorData, Long, Integer> {

    // Find readings within a specific time range (for anomaly detection feeds)
    List<SensorData> findByTimestampBetweenOrderByTimestampDesc(LocalDateTime start, LocalDateTime end);

    // Custom query to find potential anomalies based on temperature thresholds
    // Note: In a real app, this logic might be more complex or handled by the AI service
    List<SensorData> findByTemperatureGreaterThanOrderByTimestampDesc(Float threshold);

    // Group by device ID to check for device health/clustering
    // Note: We use sourceDeviceId if it exists, otherwise we'd need to add it to the entity
    // For now, let's assume we want all data for a specific device
    List<SensorData> findBySourceDeviceId(String deviceId);
}