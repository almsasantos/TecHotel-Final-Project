package com.ironhack.activityservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Update Floaties Num Dto
 */
public class UpdateFloatiesNumDto {
    /**
     * Attribute poolRentId of type Long
     */
    @NotNull(message = "Pool Rent Id cannot be null")
    private Long poolRentId;
    /**
     * Attribute floatiesNum of type Integer
     */
    @NotNull(message = "Number of floaties cannot be null")
    @Min(value = 0, message = "Number of floaties cannot be negative")
    private Integer floatiesNum;

    /**
     * UpdateFloatiesNumDto's Constructor
     * @param poolRentId receives a Long with poolRentId
     * @param floatiesNum receives a Integer with floatiesNum
     */
    public UpdateFloatiesNumDto(Long poolRentId, Integer floatiesNum) {
        this.poolRentId = poolRentId;
        this.floatiesNum = floatiesNum;
    }

    /**
     * Getter of poolRentId
     * @return a Long with poolRentId
     */
    public Long getPoolRentId() {
        return poolRentId;
    }

    /**
     * Getter of floatiesNum
     * @return an Integer with floatiesNum
     */
    public Integer getFloatiesNum() {
        return floatiesNum;
    }
}
