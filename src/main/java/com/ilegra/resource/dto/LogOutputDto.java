package com.ilegra.resource.dto;

import java.time.LocalDateTime;

public class LogOutputDto {

    private Long id;
    private String url;
    private LocalDateTime dataVisited;
    private String userId;
    private String region;

    public Long getId() {
        return id;
    }

    public LogOutputDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public LogOutputDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public LocalDateTime getDataVisited() {
        return dataVisited;
    }

    public LogOutputDto setDataVisited(LocalDateTime dataVisited) {
        this.dataVisited = dataVisited;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public LogOutputDto setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public LogOutputDto setRegion(String region) {
        this.region = region;
        return this;
    }
}
