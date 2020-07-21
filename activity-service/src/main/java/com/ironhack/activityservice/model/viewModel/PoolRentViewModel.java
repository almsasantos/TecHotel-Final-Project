package com.ironhack.activityservice.model.viewModel;

/**
 * Pool Rent View Model
 */
public class PoolRentViewModel {
    /**
     * Attribute userId of type Long
     */
    private Long userId;
    /**
     * Attribute roomId of type Integer
     */
    private Integer roomId;
    /**
     * Attribute floatiesNum of type Integer
     */
    private Integer floatiesNum;
    /**
     * Attribute towelNum of type Integer
     */
    private Integer towelNum;

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
     * Getter of floatiesNum
     * @return an Integer with floatiesNum
     */
    public Integer getFloatiesNum() {
        return floatiesNum;
    }

    /**
     * Setter of floatiesNum
     * @param floatiesNum receives an Integer with floatiesNum
     */
    public void setFloatiesNum(Integer floatiesNum) {
        this.floatiesNum = floatiesNum;
    }

    /**
     * Getter of towelNum
     * @return an Integer with towelNum
     */
    public Integer getTowelNum() {
        return towelNum;
    }

    /**
     * Setter of towelNum
     * @param towelNum an Integer with towelNum
     */
    public void setTowelNum(Integer towelNum) {
        this.towelNum = towelNum;
    }
}
