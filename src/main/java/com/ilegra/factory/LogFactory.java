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

    public LogModel create(String log) {
        String[] logArray = log.split(" ");
        return new LogModel()
                .setUrl(logArray[0])
                .setDateVisited(getDateVisited(logArray[1]))
                .setUserId(logArray[2])
                .setRegionEnum(RegionEnum.fromId(Integer.parseInt(logArray[3])));
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
