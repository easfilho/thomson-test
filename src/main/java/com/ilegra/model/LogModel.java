package com.ilegra.model;

import com.ilegra.enums.RegionEnum;

import java.time.LocalDateTime;

public class LogModel {

    private Long id;
    private String url;
    private LocalDateTime dateVisited;
    private String userId;
    private RegionEnum regionEnum;

    public Long getId() {
        return id;
    }

    public LogModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public LogModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public LocalDateTime getDateVisited() {
        return dateVisited;
    }

    public LogModel setDateVisited(LocalDateTime dateVisited) {
        this.dateVisited = dateVisited;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public LogModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public RegionEnum getRegionEnum() {
        return regionEnum;
    }

    public LogModel setRegionEnum(RegionEnum regionEnum) {
        this.regionEnum = regionEnum;
        return this;
    }
}
