package com.ilegra.repository;

import com.ilegra.enums.TemporalParameter;
import com.ilegra.factory.LogFactory;
import com.ilegra.model.LogModel;
import com.ilegra.model.MetricTimeModel;
import com.ilegra.model.MetricUrlAroundWorldModel;
import com.ilegra.model.MetricUrlPerRegionModel;
import com.ilegra.model.MetricUrlPerTemporalParameterModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LogRepository implements PanacheRepository<LogEntity> {

    private final EntityManager entityManager;
    private final LogFactory logFactory;

    public LogRepository(EntityManager entityManager, LogFactory logFactory) {
        this.entityManager = entityManager;
        this.logFactory = logFactory;
    }

    public LogModel persist(LogModel logModel) {
        LogEntity entity = logFactory.createEntity(logModel);
        persist(entity);
        logModel.setId(entity.getId());
        return logModel;
    }

    public List<MetricUrlAroundWorldModel> getTopThreeUrlAroundWorld() {
        String sql = " select new com.ilegra.model.MetricUrlAroundWorldModel(url, count(*) as quantity) " +
                " from LogEntity " +
                " group by url " +
                " order by quantity desc ";
        return entityManager.createQuery(sql, MetricUrlAroundWorldModel.class)
                .setMaxResults(3)
                .getResultList();
    }

    public List<MetricUrlPerRegionModel> getTopThreeUrlPerRegion() {
        String sql = " select new com.ilegra.model.MetricUrlPerRegionModel(count(*) as quantity, regionEnum) " +
                " from LogEntity " +
                " group by regionEnum " +
                " order by quantity desc ";
        return entityManager.createQuery(sql, MetricUrlPerRegionModel.class)
                .setMaxResults(3)
                .getResultList();
    }

    public MetricUrlAroundWorldModel getUrlLesAccessInAllWorld() {
        String sql = " select new com.ilegra.model.MetricUrlAroundWorldModel(url, count(*) as quantity) " +
                " from LogEntity " +
                " group by url " +
                " order by quantity asc ";
        try {
            return entityManager.createQuery(sql, MetricUrlAroundWorldModel.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return new MetricUrlAroundWorldModel()
                    .setUrl("No URL accessed")
                    .setQuantity(0L);
        }
    }

    public List<MetricUrlPerTemporalParameterModel> getTopThreeUrlByTemporalParameter(TemporalParameter temporalParameter) {
        String sql = " select url, avg(quantity) as averageAccess " +
                " from ( " +
                " select url, extract(" + getExtractorTimestamp(temporalParameter) + " from date_visited) as week, count(*) as quantity  from log " +
                " group by url, week) as access_per_weel " +
                " group by url " +
                " order by averageAccess desc " +
                " limit 3 ";

        List<Object[]> result = entityManager.createNativeQuery(sql).getResultList();

        return result.stream()
                .map(objects -> new MetricUrlPerTemporalParameterModel()
                        .setUrl(objects[0].toString())
                        .setAverageAccess(new BigDecimal(objects[1].toString()).setScale(2, RoundingMode.HALF_EVEN)))
                .collect(Collectors.toList());
    }

    public MetricTimeModel getTimeMoreAccess() {
        String sql = " select to_char(date_visited , 'HH:MI') as time , count(*) as quantity " +
                " from log " +
                " group by time " +
                " order by quantity desc " +
                " limit 1 ";

        try {
            Object[] result = (Object[]) entityManager.createNativeQuery(sql).getSingleResult();
            return new MetricTimeModel()
                    .setTime(LocalTime.parse(result[0].toString()))
                    .setQuantity(((BigInteger) result[1]).longValue());
        } catch (NoResultException e) {
            return new MetricTimeModel()
                    .setTime(null)
                    .setQuantity(0L);
        }
    }

    private String getExtractorTimestamp(TemporalParameter temporalParameter) {
        if (temporalParameter.equals(TemporalParameter.DAY)) {
            return "DOY";
        }

        if (temporalParameter.equals(TemporalParameter.WEEK)) {
            return "WEEK";
        }

        return "YEAR";
    }
}
