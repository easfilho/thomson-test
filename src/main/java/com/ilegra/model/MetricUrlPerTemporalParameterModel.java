package com.ilegra.model;

import java.math.BigDecimal;

public class MetricUrlPerTemporalParameterModel {

    private String url;
    private BigDecimal averageAccess;

    public MetricUrlPerTemporalParameterModel() {
    }

    public MetricUrlPerTemporalParameterModel(String url, BigDecimal averageAccess) {
        this.url = url;
        this.averageAccess = averageAccess;
    }

    public String getUrl() {
        return url;
    }

    public MetricUrlPerTemporalParameterModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public BigDecimal getAverageAccess() {
        return averageAccess;
    }

    public MetricUrlPerTemporalParameterModel setAverageAccess(BigDecimal averageAccess) {
        this.averageAccess = averageAccess;
        return this;
    }
}
