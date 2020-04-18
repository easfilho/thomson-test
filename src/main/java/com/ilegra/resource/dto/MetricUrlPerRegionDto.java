package com.ilegra.resource.dto;

public class MetricUrlPerRegionDto {

    private Long quantity;
    private String region;

    public Long getQuantity() {
        return quantity;
    }

    public MetricUrlPerRegionDto setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public MetricUrlPerRegionDto setRegion(String region) {
        this.region = region;
        return this;
    }
}
