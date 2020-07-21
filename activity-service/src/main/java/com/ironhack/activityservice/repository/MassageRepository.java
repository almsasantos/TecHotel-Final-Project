package com.ironhack.activityservice.repository;

import com.ironhack.activityservice.model.entities.Massage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Massage Repository
 */
@Repository
public interface MassageRepository extends JpaRepository<Massage, Long> {
    /**
     * Filter Massage By User Id
     * @param userId receives a Long with userId
     * @return a list of object
     */
    @Query(value = "SELECT activity_id, room_id, massage_type, balance, currency, begin_of_activity, duration, " +
            "end_of_activity FROM massages_appointments WHERE user_id = :userId;", nativeQuery = true)
    public List<Object[]> filterMassageByUserId(@Param("userId") Long userId);
}
