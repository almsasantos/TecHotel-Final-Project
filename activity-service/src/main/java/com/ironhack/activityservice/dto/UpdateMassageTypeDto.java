package com.ironhack.activityservice.dto;

import com.ironhack.activityservice.model.enums.MassageType;

import javax.validation.constraints.NotNull;

public class UpdateMassageTypeDto {
    @NotNull(message = "Massage id cannot be null")
    private Long massageId;
    @NotNull(message = "Type of Massage cannot be null")
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
