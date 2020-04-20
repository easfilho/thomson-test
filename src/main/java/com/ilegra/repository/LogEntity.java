package com.ilegra.repository;

import com.ilegra.enums.RegionEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", length = 255)
    @NotNull
    private String url;

    @Column(name = "date_visited")
    @NotNull
    private LocalDateTime dateVisited;

    @Column(name = "user_id", length = 36)
    @NotNull
    private String userId;

    @Column(name = "region", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RegionEnum regionEnum;

    public Long getId() {
        return id;
    }

    public LogEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public LogEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public LocalDateTime getDateVisited() {
        return dateVisited;
    }

    public LogEntity setDateVisited(LocalDateTime dateVisited) {
        this.dateVisited = dateVisited;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public LogEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public RegionEnum getRegionEnum() {
        return regionEnum;
    }

    public LogEntity setRegionEnum(RegionEnum regionEnum) {
        this.regionEnum = regionEnum;
        return this;
    }
}
