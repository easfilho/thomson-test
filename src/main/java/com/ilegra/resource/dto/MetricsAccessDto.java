package com.ilegra.resource.dto;

import java.util.List;

public class MetricsAccessDto {

    private List<MetricUrlAroundWorldDto> topThreeUrlAccessedAroundWorld;
    private List<MetricUrlPerRegionDto> topThreeUrlAccessedPerRegion;
    private MetricUrlAroundWorldDto  metricUrlLessAccessed;
    private List<MetricUrlPerTemporalParameterDto> topThreeUrlAccessedPerTemporalParameter;
    private MetricTimeDto metricUrlMoreAccessedByTime;

    public List<MetricUrlAroundWorldDto> getTopThreeUrlAccessedAroundWorld() {
        return topThreeUrlAccessedAroundWorld;
    }

    public MetricsAccessDto setTopThreeUrlAccessedAroundWorld(List<MetricUrlAroundWorldDto> topThreeUrlAccessedAroundWorld) {
        this.topThreeUrlAccessedAroundWorld = topThreeUrlAccessedAroundWorld;
        return this;
    }

    public List<MetricUrlPerRegionDto> getTopThreeUrlAccessedPerRegion() {
        return topThreeUrlAccessedPerRegion;
    }

    public MetricsAccessDto setTopThreeUrlAccessedPerRegion(List<MetricUrlPerRegionDto> topThreeUrlAccessedPerRegion) {
        this.topThreeUrlAccessedPerRegion = topThreeUrlAccessedPerRegion;
        return this;
    }

    public MetricUrlAroundWorldDto getMetricUrlLessAccessed() {
        return metricUrlLessAccessed;
    }

    public MetricsAccessDto setMetricUrlLessAccessed(MetricUrlAroundWorldDto metricUrlLessAccessed) {
        this.metricUrlLessAccessed = metricUrlLessAccessed;
        return this;
    }

    public List<MetricUrlPerTemporalParameterDto> getTopThreeUrlAccessedPerTemporalParameter() {
        return topThreeUrlAccessedPerTemporalParameter;
    }

    public MetricsAccessDto setTopThreeUrlAccessedPerTemporalParameter(List<MetricUrlPerTemporalParameterDto> topThreeUrlAccessedPerTemporalParameter) {
        this.topThreeUrlAccessedPerTemporalParameter = topThreeUrlAccessedPerTemporalParameter;
        return this;
    }

    public MetricTimeDto getMetricUrlMoreAccessedByTime() {
        return metricUrlMoreAccessedByTime;
    }

    public MetricsAccessDto setMetricUrlMoreAccessedByTime(MetricTimeDto metricUrlMoreAccessedByTime) {
        this.metricUrlMoreAccessedByTime = metricUrlMoreAccessedByTime;
        return this;
    }
}
