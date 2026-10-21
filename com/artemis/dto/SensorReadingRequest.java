package com.artemis.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class SensorReadingRequest {

    @NotBlank(message = "Source device ID is required")
    private String sourceDeviceId;

    @NotNull(message = "Temperature is required")
    @Min(value = -2.0, message = "Temperature must be between -2°C and 40°C")
    @Max(value = 40.0, message = "Temperature must be between -2°C and 40°C")
    private Float temperature;

    @NotNull(message = "Salinity is required")
    @Min(value = 0.0, message = "Salinity must be between 0-40 PSU")
    @Max(value = 40.0, message = "Salinity must be between 0-40 PSU")
    private Float salinity;

    @NotNull(message = "Acoustic ping reference is required")
    private String acousticPing;

    @PastOrPresent(message = "Timestamp cannot be in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestamp;

    // Getters and Setters
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
}