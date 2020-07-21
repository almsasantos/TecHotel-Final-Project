package com.ironhack.activityservice.dto;

import com.ironhack.activityservice.model.enums.MassageType;

import javax.validation.constraints.NotNull;

/**
 * Update Massage Type Dto
 */
public class UpdateMassageTypeDto {
    /**
     * Attribute massageId of type Long
     */
    @NotNull(message = "Massage id cannot be null")
    private Long massageId;
    /**
     * Attribute massageType of type MassageType
     */
    @NotNull(message = "Type of Massage cannot be null")
    private MassageType massageType;

    /**
     * UpdateMassageTypeDto's Constructor
     * @param massageId receives a Long with massageId
     * @param massageType receives a MassageType with massageType
     */
    public UpdateMassageTypeDto(Long massageId, MassageType massageType) {
        this.massageId = massageId;
        this.massageType = massageType;
    }

    /**
     * Getter of massageId
     * @return a Long with massageId
     */
    public Long getMassageId() {
        return massageId;
    }

    /**
     * Getter of massageType
     * @return a MassageType with massageType
     */
    public MassageType getMassageType() {
        return massageType;
    }
}
