package com.ironhack.edgeservice.model.entities.activities;

import com.ironhack.edgeservice.model.enums.MassageType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Massage extends Activity {
    private MassageType massageType;
    private LocalDateTime beginOfActivity;
    private LocalTime duration;
    private LocalDateTime endOfActivity;

    public Massage() {
        this.duration = LocalTime.of(1, 00);
    }

    public Massage(Long userId, Integer roomId, MassageType massageType, LocalDateTime beginOfActivity) {
        super(userId, roomId);
        this.massageType = massageType;
        this.beginOfActivity = beginOfActivity;
        this.duration = LocalTime.of(1, 00);
        this.endOfActivity = beginOfActivity.plus(Duration.ofHours(duration.getHour()));
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

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public LocalDateTime getEndOfActivity() {
        return endOfActivity;
    }

    public void setEndOfActivity(LocalDateTime endOfActivity) {
        this.endOfActivity = endOfActivity;
    }
}
