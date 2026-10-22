package com.artemis.entity;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_data")
@Audited
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float temperature;
    private Float salinity;
    private String acousticPing;
    private String sourceDeviceId;
    private LocalDateTime timestamp;

    public SensorData() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getSalinity() {
        return salinity;
    }

    public void setSalinity(Float salinity) {
        this.salinity = salinity;
    }

    public String getAcousticPing() {
        return acousticPing;
    }

    public void setAcousticPing(String acousticPing) {
        this.acousticPing = acousticPing;
    }

    public String getSourceDeviceId() {
        return sourceDeviceId;
    }

    public void setSourceDeviceId(String sourceDeviceId) {
        this.sourceDeviceId = sourceDeviceId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
