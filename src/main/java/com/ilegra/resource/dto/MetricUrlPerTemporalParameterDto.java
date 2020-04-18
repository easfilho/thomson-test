package com.ilegra.resource.dto;

import java.math.BigDecimal;

public class MetricUrlPerTemporalParameterDto {

    private String url;
    private BigDecimal averageAccess;

    public String getUrl() {
        return url;
    }

    public MetricUrlPerTemporalParameterDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public BigDecimal getAverageAccess() {
        return averageAccess;
    }

    public MetricUrlPerTemporalParameterDto setAverageAccess(BigDecimal averageAccess) {
        this.averageAccess = averageAccess;
        return this;
    }
}
