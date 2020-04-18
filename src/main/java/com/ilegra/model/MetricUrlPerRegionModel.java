package com.ilegra.model;

import com.ilegra.enums.RegionEnum;

public class MetricUrlPerRegionModel {

    private Long quantity;
    private RegionEnum regionEnum;

    public MetricUrlPerRegionModel() {
    }

    public MetricUrlPerRegionModel(Long quantity, RegionEnum regionEnum) {
        this.quantity = quantity;
        this.regionEnum = regionEnum;
    }

    public Long getQuantity() {
        return quantity;
    }

    public MetricUrlPerRegionModel setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public RegionEnum getRegionEnum() {
        return regionEnum;
    }

    public MetricUrlPerRegionModel setRegionEnum(RegionEnum regionEnum) {
        this.regionEnum = regionEnum;
        return this;
    }
}
