package com.ironhack.edgeservice.dto;

public class UpdateTowelNumDto {
    private Long poolRentId;
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
