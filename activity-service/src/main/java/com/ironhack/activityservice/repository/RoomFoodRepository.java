package com.ironhack.activityservice.repository;

import com.ironhack.activityservice.model.entities.RoomFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFoodRepository extends JpaRepository<RoomFood, Long> {
}
