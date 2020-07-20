package com.ironhack.reservationservice.repository;

import com.ironhack.reservationservice.model.entities.UserReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReservationRepository extends JpaRepository<UserReservation, Long> {
}
