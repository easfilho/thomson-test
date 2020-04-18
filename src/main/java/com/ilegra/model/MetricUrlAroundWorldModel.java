package com.ilegra.model;

public class MetricUrlAroundWorldModel {

    private String url;
    private Long quantity;

    public MetricUrlAroundWorldModel() {
    }

    public MetricUrlAroundWorldModel(String url, Long quantity) {
        this.url = url;
        this.quantity = quantity;
    }

    public String getUrl() {
        return url;
    }

    public MetricUrlAroundWorldModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public MetricUrlAroundWorldModel setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
