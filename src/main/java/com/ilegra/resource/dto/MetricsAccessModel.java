package com.ilegra.resource.dto;

import java.util.List;

public class MetricsAccessModel {

    private List<MetricUrlAroundWorldDto> topThreeUrlAccessedAroundWorld;
    private List<MetricUrlPerRegionDto> topThreeUrlAccessedPerRegion;
    private MetricUrlAroundWorldDto metricUrlLessAccessed;
    private List<MetricUrlPerTemporalParameterDto> topThreeUrlAccessedPerTemporalParameter;
    private MetricTimeDto metricUrlMoreAccessedByTime;

    public List<MetricUrlAroundWorldDto> getTopThreeUrlAccessedAroundWorld() {
        return topThreeUrlAccessedAroundWorld;
    }

    public MetricsAccessModel setTopThreeUrlAccessedAroundWorld(List<MetricUrlAroundWorldDto> topThreeUrlAccessedAroundWorld) {
        this.topThreeUrlAccessedAroundWorld = topThreeUrlAccessedAroundWorld;
        return this;
    }

    public List<MetricUrlPerRegionDto> getTopThreeUrlAccessedPerRegion() {
        return topThreeUrlAccessedPerRegion;
    }

    public MetricsAccessModel setTopThreeUrlAccessedPerRegion(List<MetricUrlPerRegionDto> topThreeUrlAccessedPerRegion) {
        this.topThreeUrlAccessedPerRegion = topThreeUrlAccessedPerRegion;
        return this;
    }

    public MetricUrlAroundWorldDto getMetricUrlLessAccessed() {
        return metricUrlLessAccessed;
    }

    public MetricsAccessModel setMetricUrlLessAccessed(MetricUrlAroundWorldDto metricUrlLessAccessed) {
        this.metricUrlLessAccessed = metricUrlLessAccessed;
        return this;
    }

    public List<MetricUrlPerTemporalParameterDto> getTopThreeUrlAccessedPerTemporalParameter() {
        return topThreeUrlAccessedPerTemporalParameter;
    }

    public MetricsAccessModel setTopThreeUrlAccessedPerTemporalParameter(List<MetricUrlPerTemporalParameterDto> topThreeUrlAccessedPerTemporalParameter) {
        this.topThreeUrlAccessedPerTemporalParameter = topThreeUrlAccessedPerTemporalParameter;
        return this;
    }

    public MetricTimeDto getMetricUrlMoreAccessedByTime() {
        return metricUrlMoreAccessedByTime;
    }

    public MetricsAccessModel setMetricUrlMoreAccessedByTime(MetricTimeDto metricUrlMoreAccessedByTime) {
        this.metricUrlMoreAccessedByTime = metricUrlMoreAccessedByTime;
        return this;
    }
}
