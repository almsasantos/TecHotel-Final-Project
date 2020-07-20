package com.ironhack.activityservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateFloatiesNumDto {
    @NotNull(message = "Pool Rent Id cannot be null")
    private Long poolRentId;
    @NotNull(message = "Number of floaties cannot be null")
    @Min(value = 0, message = "Number of floaties cannot be negative")
    private Integer floatiesNum;

    public UpdateFloatiesNumDto(Long poolRentId, Integer floatiesNum) {
        this.poolRentId = poolRentId;
        this.floatiesNum = floatiesNum;
    }

    public Long getPoolRentId() {
        return poolRentId;
    }

    public Integer getFloatiesNum() {
        return floatiesNum;
    }
}
