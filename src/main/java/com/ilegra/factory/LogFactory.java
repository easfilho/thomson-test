package com.ilegra.factory;

import com.ilegra.enums.RegionEnum;
import com.ilegra.model.LogModel;
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

    public LogModel create(String log) {
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

    public LogOutputDto create(LogModel logModel) {
        return new LogOutputDto()
                .setId(logModel.getId())
                .setUrl(logModel.getUrl())
                .setDataVisited(logModel.getDateVisited())
                .setUserId(logModel.getUserId())
                .setRegion(logModel.getRegionEnum().getName());
    }
}
