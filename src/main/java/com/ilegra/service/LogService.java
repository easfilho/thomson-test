package com.ilegra.service;

import com.ilegra.enums.TemporalParameter;
import com.ilegra.factory.LogFactory;
import com.ilegra.factory.MetricsAccessFactory;
import com.ilegra.model.LogModel;
import com.ilegra.model.MetricTimeModel;
import com.ilegra.model.MetricUrlAroundWorldModel;
import com.ilegra.model.MetricUrlPerRegionModel;
import com.ilegra.model.MetricUrlPerTemporalParameterModel;
import com.ilegra.model.MetricsAccessModel;
import com.ilegra.repository.LogRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class LogService {

    private final LogFactory logFactory;
    private final LogRepository logRepository;
    private final MetricsAccessFactory metricsAccessFactory;

    public LogService(LogFactory logFactory,
                      LogRepository logRepository,
                      MetricsAccessFactory metricsAccessFactory) {
        this.logFactory = logFactory;
        this.logRepository = logRepository;
        this.metricsAccessFactory = metricsAccessFactory;
    }

    public LogModel save(String log) {
        LogModel logModel = logFactory.createModel(log);
        return logRepository.persist(logModel);
    }

    public MetricsAccessModel getMetrics() {
        List<MetricUrlAroundWorldModel> topThreeUrlAroundWorld = logRepository.getTopThreeUrlAroundWorld();
        List<MetricUrlPerRegionModel> topThreeUrlPerRegion = logRepository.getTopThreeUrlPerRegion();
        MetricUrlAroundWorldModel urlLesAccessInAllWorld = logRepository.getUrlLesAccessInAllWorld();
        List<MetricUrlPerTemporalParameterModel> topThreeUrlByTemporalParameter = logRepository.getTopThreeUrlByTemporalParameter(TemporalParameter.WEEK);
        MetricTimeModel metricTimeModel = logRepository.getTimeMoreAccess();
        return metricsAccessFactory.create(topThreeUrlAroundWorld,
                                           topThreeUrlPerRegion,
                                           urlLesAccessInAllWorld,
                                           topThreeUrlByTemporalParameter,
                                           metricTimeModel);
    }
}
