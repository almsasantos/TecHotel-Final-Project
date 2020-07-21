package com.ironhack.activityservice.model.viewModel;

import com.ironhack.activityservice.model.enums.MassageType;

/**
 * Massage View Model
 */
public class MassageViewModel {
    /**
     * Attribute userId of type Long
     */
    private Long userId;
    /**
     * Attribute roomId of type Integer
     */
    private Integer roomId;
    /**
     * Attribute massageType of type MassageType
     */
    private MassageType massageType;

    /**
     * Getter of userId
     * @return a Long with userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter of userId
     * @param userId receives a Long with userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter of roomId
     * @return a Integer with roomId
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * Setter of roomId
     * @param roomId receives a Integer with roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * Getter of massageType
     * @return a MassageType with massageType
     */
    public MassageType getMassageType() {
        return massageType;
    }

    /**
     * Setter of massageType
     * @param massageType receives a MassageType with massageType
     */
    public void setMassageType(MassageType massageType) {
        this.massageType = massageType;
    }
}
