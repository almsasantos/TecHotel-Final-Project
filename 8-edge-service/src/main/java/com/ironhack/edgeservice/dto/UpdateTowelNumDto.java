package com.ironhack.edgeservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Update Towel Num Dto
 */
public class UpdateTowelNumDto {
    /**
     * Attribute massageId of type Long
     */
    @NotNull(message = "Pool Rent id cannot be null")
    private Long poolRentId;
    /**
     * Attribute massageId of type Long
     */
    @NotNull(message = "Number of towels cannot be null")
    @Min(value = 0, message = "Number of towels cannot be negative")
    private Integer towelNum;

    /**
     * UpdateTowelNumDto's Constructor
     * @param poolRentId receives a Long with poolRentId
     * @param towelNum receives a Integer with towelNum
     */
    public UpdateTowelNumDto(Long poolRentId, Integer towelNum) {
        this.poolRentId = poolRentId;
        this.towelNum = towelNum;
    }

    /**
     * Getter of poolRentId
     * @return a Long with poolRentId
     */
    public Long getPoolRentId() {
        return poolRentId;
    }

    /**
     * Getter of towelNum
     * @return an Integer with towelNum
     */
    public Integer getTowelNum() {
        return towelNum;
    }
}
