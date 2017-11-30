package com.example.genericdetector.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detectortaskconfig")
public class DetectorTaskConfig {

    @Id
    private String detectorName;
    private Integer timeRange;
    private String pollDate;
    private Integer fixedDelay;
    private String enabled;

    public String getDetectorName() {
        return detectorName;
    }

    public void setDetectorName(String detectorName) {
        this.detectorName = detectorName;
    }

    public Integer getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(Integer timeRange) {
        this.timeRange = timeRange;
    }

    public String getPollDate() {
        return pollDate;
    }

    public void setPollDate(String pollDate) {
        this.pollDate = pollDate;
    }

    public Integer getFixedDelay() {
        return fixedDelay;
    }

    public void setFixedDelay(Integer fixedDelay) {
        this.fixedDelay = fixedDelay;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetectorTaskConfig that = (DetectorTaskConfig) o;

        if (detectorName != null ? !detectorName.equals(that.detectorName) : that.detectorName != null) return false;
        if (timeRange != null ? !timeRange.equals(that.timeRange) : that.timeRange != null) return false;
        if (pollDate != null ? !pollDate.equals(that.pollDate) : that.pollDate != null) return false;
        if (fixedDelay != null ? !fixedDelay.equals(that.fixedDelay) : that.fixedDelay != null) return false;
        return enabled != null ? enabled.equals(that.enabled) : that.enabled == null;
    }


    @Override
    public String toString() {
        return "DetectorTaskConfig{" +
                "detectorName='" + detectorName + '\'' +
                ", timeRange=" + timeRange +
                ", pollDate='" + pollDate + '\'' +
                ", fixedDelay=" + fixedDelay +
                ", enabled='" + enabled + '\'' +
                '}';
    }
}
