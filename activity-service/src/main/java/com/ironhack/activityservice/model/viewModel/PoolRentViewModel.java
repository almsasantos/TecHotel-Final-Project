package com.ironhack.activityservice.model.viewModel;

import java.time.LocalDateTime;

public class PoolRentViewModel {
    private Long userId;
    private Integer roomId;
    private Integer floatiesNum;
    private Integer towelNum;
    private LocalDateTime beginOfActivity;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getFloatiesNum() {
        return floatiesNum;
    }

    public void setFloatiesNum(Integer floatiesNum) {
        this.floatiesNum = floatiesNum;
    }

    public Integer getTowelNum() {
        return towelNum;
    }

    public void setTowelNum(Integer towelNum) {
        this.towelNum = towelNum;
    }

    public LocalDateTime getBeginOfActivity() {
        return beginOfActivity;
    }

    public void setBeginOfActivity(LocalDateTime beginOfActivity) {
        this.beginOfActivity = beginOfActivity;
    }
}
