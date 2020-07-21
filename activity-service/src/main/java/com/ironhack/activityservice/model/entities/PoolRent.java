package com.ironhack.activityservice.model.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "pool_rents")
@PrimaryKeyJoinColumn(name = "activityId")
public class PoolRent extends Activity {
    @Min(value=0, message = "Number of floaties cannot be negative")
    private Integer floatiesNum;
    @Min(value=0, message = "Number of towels cannot be negative")
    private Integer towelNum;
    private LocalDateTime beginOfActivity;
    private LocalTime duration;
    private LocalDateTime endOfActivity;

    public PoolRent() {
        this.beginOfActivity = LocalDateTime.now();
        this.duration = LocalTime.of(1, 00);
    }

    public PoolRent(Long userId, Integer roomId, Integer floatiesNum, Integer towelNum) {
        super(userId, roomId);
        this.floatiesNum = floatiesNum;
        this.towelNum = towelNum;
        this.beginOfActivity = LocalDateTime.now();
        this.endOfActivity = beginOfActivity.plus(Duration.ofHours(duration.getHour()));
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
