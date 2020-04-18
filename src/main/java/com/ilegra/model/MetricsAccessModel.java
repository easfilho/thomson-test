package com.ilegra.model;

import java.util.List;

public class MetricsAccessModel {

    private List<MetricUrlAroundWorldModel> topThreeUrlAccessedAroundWorld;
    private List<MetricUrlPerRegionModel> topThreeUrlAccessedPerRegion;
    private MetricUrlAroundWorldModel  metricUrlLessAccessed;
    private List<MetricUrlPerTemporalParameterModel> topThreeUrlAccessedPerTemporalParameter;
    private MetricTimeModel metricUrlMoreAccessedByTime;

    public List<MetricUrlAroundWorldModel> getTopThreeUrlAccessedAroundWorld() {
        return topThreeUrlAccessedAroundWorld;
    }

    public MetricsAccessModel setTopThreeUrlAccessedAroundWorld(List<MetricUrlAroundWorldModel> topThreeUrlAccessedAroundWorld) {
        this.topThreeUrlAccessedAroundWorld = topThreeUrlAccessedAroundWorld;
        return this;
    }

    public List<MetricUrlPerRegionModel> getTopThreeUrlAccessedPerRegion() {
        return topThreeUrlAccessedPerRegion;
    }

    public MetricsAccessModel setTopThreeUrlAccessedPerRegion(List<MetricUrlPerRegionModel> topThreeUrlAccessedPerRegion) {
        this.topThreeUrlAccessedPerRegion = topThreeUrlAccessedPerRegion;
        return this;
    }

    public MetricUrlAroundWorldModel getMetricUrlLessAccessed() {
        return metricUrlLessAccessed;
    }

    public MetricsAccessModel setMetricUrlLessAccessed(MetricUrlAroundWorldModel metricUrlLessAccessed) {
        this.metricUrlLessAccessed = metricUrlLessAccessed;
        return this;
    }

    public List<MetricUrlPerTemporalParameterModel> getTopThreeUrlAccessedPerTemporalParameter() {
        return topThreeUrlAccessedPerTemporalParameter;
    }

    public MetricsAccessModel setTopThreeUrlAccessedPerTemporalParameter(List<MetricUrlPerTemporalParameterModel> topThreeUrlAccessedPerTemporalParameter) {
        this.topThreeUrlAccessedPerTemporalParameter = topThreeUrlAccessedPerTemporalParameter;
        return this;
    }

    public MetricTimeModel getMetricUrlMoreAccessedByTime() {
        return metricUrlMoreAccessedByTime;
    }

    public MetricsAccessModel setMetricUrlMoreAccessedByTime(MetricTimeModel metricUrlMoreAccessedByTime) {
        this.metricUrlMoreAccessedByTime = metricUrlMoreAccessedByTime;
        return this;
    }
}
