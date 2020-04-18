package com.ilegra.resource.dto;

public class MetricUrlAroundWorldDto {

    private String url;
    private Integer quantity;

    public MetricUrlAroundWorldDto() {
    }

    public MetricUrlAroundWorldDto(String url, Integer quantity) {
        this.url = url;
        this.quantity = quantity;
    }

    public String getUrl() {
        return url;
    }

    public MetricUrlAroundWorldDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public MetricUrlAroundWorldDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
