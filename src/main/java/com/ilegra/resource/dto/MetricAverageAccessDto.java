package com.ilegra.resource.dto;

public class MetricAverageAccessDto {

    private String url;
    private Double averageAccess;

    public String getUrl() {
        return url;
    }

    public MetricAverageAccessDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public Double getAverageAccess() {
        return averageAccess;
    }

    public MetricAverageAccessDto setAverageAccess(Double averageAccess) {
        this.averageAccess = averageAccess;
        return this;
    }
}
