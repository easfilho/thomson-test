package com.ilegra.resource.dto;

import javax.validation.constraints.NotBlank;

public class LogInputDto {

    @NotBlank(message = "Log can not be empty")
    private String log;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
