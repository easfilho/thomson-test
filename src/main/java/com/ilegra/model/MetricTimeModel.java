package com.ilegra.model;

import java.time.LocalTime;

public class MetricTimeModel {

    private LocalTime time;
    private Long quantity;

    public MetricTimeModel() {
    }

    public MetricTimeModel(LocalTime time, Long quantity) {
        this.time = time;
        this.quantity = quantity;
    }

    public LocalTime getTime() {
        return time;
    }

    public MetricTimeModel setTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public MetricTimeModel setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
