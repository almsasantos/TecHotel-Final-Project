package com.ironhack.invoiceservice.model.activities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PoolRent extends Activity {
    private Integer floatiesNum;
    private Integer towelNum;
    private LocalDateTime beginOfActivity;
    private LocalTime duration;
    private LocalDateTime endOfActivity;

    public PoolRent() {}

    public PoolRent(Long userId, Integer roomId, Integer floatiesNum, Integer towelNum,LocalDateTime beginOfActivity) {
        super(userId, roomId);
        this.floatiesNum = floatiesNum;
        this.towelNum = towelNum;
        this.beginOfActivity = beginOfActivity;
        this.duration = LocalTime.of(1, 00);
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
