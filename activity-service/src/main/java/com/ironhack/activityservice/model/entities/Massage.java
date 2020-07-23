package com.ironhack.activityservice.model.entities;

import com.ironhack.activityservice.model.enums.MassageType;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Massage's Table
 */
@Entity
@Table(name = "massages_appointments")
@PrimaryKeyJoinColumn(name = "activityId")
public class Massage extends Activity {
    /**
     * Attribute massageType of MassageType
     */
    @Enumerated(EnumType.STRING)
    private MassageType massageType;
    /**
     * Attribute beginOfActivity of LocalDateTime
     */
    private LocalDateTime beginOfActivity;
    /**
     * Attribute duration of LocalTime
     */
    private LocalTime duration;
    /**
     * Attribute endOfActivity of LocalDateTime
     */
    private LocalDateTime endOfActivity;

    /**
     * Empty Massage's Constructor
     */
    public Massage() {
        this.beginOfActivity = LocalDateTime.now();
        this.duration = LocalTime.of(1, 00);
    }

    /**
     * Massage's Constructor
     * @param userId receives a Long with userId
     * @param roomId receives a Integer with roomId
     * @param massageType receives a MassageType with massageType
     */
    public Massage(Long userId, Integer roomId, MassageType massageType, LocalDateTime beginOfActivity) {
        super(userId, roomId);
        this.massageType = massageType;
        this.beginOfActivity = beginOfActivity;
        this.duration = LocalTime.of(1, 00);
        this.endOfActivity = beginOfActivity.plus(Duration.ofHours(duration.getHour()));
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

    /**
     * Getter of beginOfActivity
     * @return a LocalDateTime with beginOfActivity
     */
    public LocalDateTime getBeginOfActivity() {
        return beginOfActivity;
    }

    /**
     * Setter of beginOfActivity
     * @param beginOfActivity receives a LocalDateTime with beginOfActivity
     */
    public void setBeginOfActivity(LocalDateTime beginOfActivity) {
        this.beginOfActivity = beginOfActivity;
    }

    /**
     * Getter of duration
     * @return a LocalTime with duration
     */
    public LocalTime getDuration() {
        return duration;
    }

    /**
     * Setter of duration
     * @param duration receives a LocalTime with duration
     */
    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    /**
     * Getter of endOfActivity
     * @return a LocalDateTime with endOfActivity
     */
    public LocalDateTime getEndOfActivity() {
        return endOfActivity;
    }

    /**
     * Setter of endOfActivity
     * @param endOfActivity receives a LocalDateTime with endOfActivity
     */
    public void setEndOfActivity(LocalDateTime endOfActivity) {
        this.endOfActivity = endOfActivity;
    }
}
