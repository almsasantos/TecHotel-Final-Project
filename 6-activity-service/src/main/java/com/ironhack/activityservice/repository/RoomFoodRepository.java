package com.ironhack.activityservice.repository;

import com.ironhack.activityservice.model.entities.RoomFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Room Food Repository
 */
@Repository
public interface RoomFoodRepository extends JpaRepository<RoomFood, Long> {
    /**
     * Filter Room Food By User Id
     * @param userId receives a Long with userId
     * @return a list of object
     */

    @Query(value = "SELECT * FROM room_food_services WHERE user_id = :userId", nativeQuery = true)
    public List<RoomFood> filterRoomFoodByUserId(@Param("userId") Long userId);
}
