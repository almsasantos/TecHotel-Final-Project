package com.ironhack.edgeservice.dto;

public class UpdateFloatiesNumDto {
    private Long poolRentId;
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
