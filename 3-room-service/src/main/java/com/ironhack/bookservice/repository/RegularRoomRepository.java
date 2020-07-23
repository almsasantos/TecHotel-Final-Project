package com.ironhack.bookservice.repository;

import com.ironhack.bookservice.model.RegularRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of Regular Room
 */
@Repository
public interface RegularRoomRepository extends JpaRepository<RegularRoom, Integer> {
}
