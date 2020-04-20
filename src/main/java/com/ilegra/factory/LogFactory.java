package com.ilegra.factory;

import com.ilegra.enums.RegionEnum;
import com.ilegra.model.LogModel;
import com.ilegra.repository.LogEntity;
import com.ilegra.resource.dto.LogOutputDto;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@ApplicationScoped
public class LogFactory {

    private static final int URL_INDEX = 0;
    private static final int DATE_VISITED_INDEX = 1;
    private static final int USER_ID_INDEX = 2;
    private static final int REGION_INDEX = 3;

    public LogModel createModel(String log) {
        String[] logArray = log.split(" ");
        return new LogModel()
                .setUrl(logArray[URL_INDEX])
                .setDateVisited(getDateVisited(logArray[DATE_VISITED_INDEX]))
                .setUserId(logArray[USER_ID_INDEX])
                .setRegionEnum(RegionEnum.fromId(Integer.parseInt(logArray[REGION_INDEX])));
    }

    private LocalDateTime getDateVisited(String timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(timestamp)),
                                       TimeZone.getDefault().toZoneId());
    }

    public LogOutputDto createModel(LogModel logModel) {
        return new LogOutputDto()
                .setId(logModel.getId())
                .setUrl(logModel.getUrl())
                .setDateVisited(logModel.getDateVisited())
                .setUserId(logModel.getUserId())
                .setRegion(logModel.getRegionEnum().getName());
    }

    public LogEntity createEntity(LogModel logModel) {
        return new LogEntity()
                .setId(logModel.getId())
                .setUrl(logModel.getUrl())
                .setDateVisited(logModel.getDateVisited())
                .setUserId(logModel.getUserId())
                .setRegionEnum(logModel.getRegionEnum());
    }
}
