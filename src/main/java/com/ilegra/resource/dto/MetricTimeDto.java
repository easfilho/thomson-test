package com.ilegra.resource.dto;

public class MetricTimeDto {

    private String time;
    private Long quantity;

    public String getTime() {
        return time;
    }

    public MetricTimeDto setTime(String time) {
        this.time = time;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public MetricTimeDto setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
