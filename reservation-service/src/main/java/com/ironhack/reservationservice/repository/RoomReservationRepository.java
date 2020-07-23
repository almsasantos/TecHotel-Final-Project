package com.ironhack.reservationservice.repository;

import com.ironhack.reservationservice.model.entities.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Room Reservation Repository
 */
@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
}
