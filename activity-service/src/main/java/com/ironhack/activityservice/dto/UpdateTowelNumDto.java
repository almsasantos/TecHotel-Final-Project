package com.ironhack.activityservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateTowelNumDto {
    @NotNull(message = "Pool Rent id cannot be null")
    private Long poolRentId;
    @NotNull(message = "Number of towels cannot be null")
    @Min(value = 0, message = "Number of towels cannot be negative")
    private Integer towelNum;

    public UpdateTowelNumDto(Long poolRentId, Integer towelNum) {
        this.poolRentId = poolRentId;
        this.towelNum = towelNum;
    }

    public Long getPoolRentId() {
        return poolRentId;
    }

    public Integer getTowelNum() {
        return towelNum;
    }
}
