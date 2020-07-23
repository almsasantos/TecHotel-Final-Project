package com.ironhack.edgeservice.model.viewModel;

import com.ironhack.edgeservice.model.enums.MassageType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Massage View Model
 */
public class MassageViewModel {
    /**
     * Attribute userId of type Long
     */
    @NotNull(message = "User id cannot be null")
    @Min(value = 1 , message = "User id cannot be less than one")
    private Long userId;
    /**
     * Attribute roomId of type Integer
     */
    @NotNull(message = "Room id cannot be null")
    @Min(value = 1 , message = "Room id cannot be less than one")
    private Integer roomId;
    /**
     * Attribute massageType of type MassageType
     */
    @NotNull(message = "Massage Type cannot be null")
    private MassageType massageType;

    private LocalDateTime beginOfActivity;

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

    public LocalDateTime getBeginOfActivity() {
        return beginOfActivity;
    }

    public void setBeginOfActivity(LocalDateTime beginOfActivity) {
        this.beginOfActivity = beginOfActivity;
    }
}
