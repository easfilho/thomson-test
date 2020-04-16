package com.ilegra.model;

import com.ilegra.enums.RegionEnum;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "date_visited")
    private LocalDateTime dateVisited;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "region")
    @Enumerated(EnumType.STRING)
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
