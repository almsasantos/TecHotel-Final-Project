package com.ironhack.activityservice.model.entities;

import com.ironhack.activityservice.model.enums.MassageType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "massages_appointments")
@PrimaryKeyJoinColumn(name = "activityId")
public class Massage extends Activity {
    @Enumerated(EnumType.STRING)
    private MassageType massageType;
    @NotNull
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
