package com.artemis.dto;

import java.time.LocalDateTime;

public class SensorReadingResponse {
    private Long id;
    private String sourceDeviceId;
    private Float temperature;
    private Float salinity;
    private String acousticPing;
    private LocalDateTime timestamp;
    private boolean isAnomalySuspected;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSourceDeviceId() { return sourceDeviceId; }
    public void setSourceDeviceId(String sourceDeviceId) { this.sourceDeviceId = sourceDeviceId; }

    public Float getTemperature() { return temperature; }
    public void setTemperature(Float temperature) { this.temperature = temperature; }

    public Float getSalinity() { return salinity; }
    public void setSalinity(Float salinity) { this.salinity = salinity; }

    public String getAcousticPing() { return acousticPing; }
    public void setAcousticPing(String acousticPing) { this.acousticPing = acousticPing; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public boolean isAnomalySuspected() { return isAnomalySuspected; }
    public void setAnomalySuspected(boolean anomalySuspected) { isAnomalySuspected = anomalySuspected; }
}