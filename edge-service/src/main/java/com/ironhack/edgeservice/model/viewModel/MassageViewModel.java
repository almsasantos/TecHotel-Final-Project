package com.ironhack.edgeservice.model.viewModel;

import com.ironhack.edgeservice.model.enums.MassageType;

public class MassageViewModel {
    private Long userId;
    private Integer roomId;
    private MassageType massageType;

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

    public MassageType getMassageType() {
        return massageType;
    }

    public void setMassageType(MassageType massageType) {
        this.massageType = massageType;
    }
}
