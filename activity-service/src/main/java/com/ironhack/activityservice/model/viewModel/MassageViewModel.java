package com.ironhack.activityservice.model.viewModel;

import com.ironhack.activityservice.model.enums.MassageType;

import java.time.LocalDateTime;

public class MassageViewModel {
    private Long userId;
    private Integer roomId;
    private MassageType massageType;
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

    public MassageType getMassageType() {
        return massageType;
    }

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
