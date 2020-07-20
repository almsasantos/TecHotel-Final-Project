package com.ironhack.edgeservice.dto;

import com.ironhack.edgeservice.model.enums.MassageType;

public class UpdateMassageTypeDto {
    private Long massageId;
    private MassageType massageType;

    public UpdateMassageTypeDto(Long massageId, MassageType massageType) {
        this.massageId = massageId;
        this.massageType = massageType;
    }

    public Long getMassageId() {
        return massageId;
    }

    public MassageType getMassageType() {
        return massageType;
    }
}
