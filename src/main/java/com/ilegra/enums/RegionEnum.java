package com.ilegra.enums;

import java.util.Arrays;

public enum RegionEnum {

    US_EAST_1(1, "us-east-1"),
    US_WEST_2(2, "us-west-2"),
    AP_SOUTH_1(3, "ap-south-1");

    private Integer id;
    private String name;

    RegionEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static RegionEnum fromId(Integer id) {
        return Arrays.stream(values())
                .filter(region -> region.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found region with id " + id));
    }
}
