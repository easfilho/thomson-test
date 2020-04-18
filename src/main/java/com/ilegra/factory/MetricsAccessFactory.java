package com.ilegra.factory;

import com.ilegra.model.MetricTimeModel;
import com.ilegra.model.MetricUrlAroundWorldModel;
import com.ilegra.model.MetricUrlPerRegionModel;
import com.ilegra.model.MetricUrlPerTemporalParameterModel;
import com.ilegra.model.MetricsAccessModel;
import com.ilegra.resource.dto.MetricTimeDto;
import com.ilegra.resource.dto.MetricUrlAroundWorldDto;
import com.ilegra.resource.dto.MetricUrlPerRegionDto;
import com.ilegra.resource.dto.MetricUrlPerTemporalParameterDto;
import com.ilegra.resource.dto.MetricsAccessDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MetricsAccessFactory {


    public MetricsAccessModel create(List<MetricUrlAroundWorldModel> topThreeUrlAccessedAroundWorld,
                                     List<MetricUrlPerRegionModel> topThreeUrlAccessedPerRegion,
                                     MetricUrlAroundWorldModel metricUrlLessAccessed,
                                     List<MetricUrlPerTemporalParameterModel> topThreeUrlAccessedPerTemporalParameter,
                                     MetricTimeModel metricUrlMoreAccessedByTime) {
        return new MetricsAccessModel()
                .setTopThreeUrlAccessedAroundWorld(topThreeUrlAccessedAroundWorld)
                .setTopThreeUrlAccessedPerRegion(topThreeUrlAccessedPerRegion)
                .setMetricUrlLessAccessed(metricUrlLessAccessed)
                .setTopThreeUrlAccessedPerTemporalParameter(topThreeUrlAccessedPerTemporalParameter)
                .setMetricUrlMoreAccessedByTime(metricUrlMoreAccessedByTime);
    }

    public MetricsAccessDto create(MetricsAccessModel metricsAccessModel) {
        return new MetricsAccessDto()
                .setTopThreeUrlAccessedAroundWorld(getMetricUrlAroundWorldDto(metricsAccessModel.getTopThreeUrlAccessedAroundWorld()))
                .setTopThreeUrlAccessedPerRegion(getMetricUrlPerRegionDto(metricsAccessModel.getTopThreeUrlAccessedPerRegion()))
                .setMetricUrlLessAccessed(getMetricUrlAroundWorldDto(metricsAccessModel.getMetricUrlLessAccessed()))
                .setTopThreeUrlAccessedPerTemporalParameter(getMetricUrlPerTemporalParameterDto(metricsAccessModel.getTopThreeUrlAccessedPerTemporalParameter()))
                .setMetricUrlMoreAccessedByTime(getMetricTimeDto(metricsAccessModel.getMetricUrlMoreAccessedByTime()));
    }

    private List<MetricUrlAroundWorldDto> getMetricUrlAroundWorldDto(List<MetricUrlAroundWorldModel> metrics) {
        return metrics.stream()
                .map(metricUrlAroundWorldModel -> new MetricUrlAroundWorldDto()
                        .setUrl(metricUrlAroundWorldModel.getUrl())
                        .setQuantity(metricUrlAroundWorldModel.getQuantity().intValue()))
                .collect(Collectors.toList());
    }

    private List<MetricUrlPerRegionDto> getMetricUrlPerRegionDto(List<MetricUrlPerRegionModel> metrics) {
        return metrics.stream()
                .map(metricUrlPerRegionModel -> new MetricUrlPerRegionDto()
                        .setQuantity(metricUrlPerRegionModel.getQuantity())
                        .setRegion(metricUrlPerRegionModel.getRegionEnum().getName()))
                .collect(Collectors.toList());
    }

    private MetricUrlAroundWorldDto getMetricUrlAroundWorldDto(MetricUrlAroundWorldModel metric) {
        return new MetricUrlAroundWorldDto()
                .setUrl(metric.getUrl())
                .setQuantity(metric.getQuantity().intValue());
    }

    private List<MetricUrlPerTemporalParameterDto> getMetricUrlPerTemporalParameterDto(List<MetricUrlPerTemporalParameterModel> metrics) {
        return metrics.stream()
                .map(metricUrlPerTemporalParameterModel -> new MetricUrlPerTemporalParameterDto()
                        .setUrl(metricUrlPerTemporalParameterModel.getUrl())
                        .setAverageAccess(metricUrlPerTemporalParameterModel.getAverageAccess())
                )
                .collect(Collectors.toList());
    }

    private MetricTimeDto getMetricTimeDto(MetricTimeModel metric) {
        return new MetricTimeDto()
                .setTime(metric.getTime().toString())
                .setQuantity(metric.getQuantity());
    }
}
