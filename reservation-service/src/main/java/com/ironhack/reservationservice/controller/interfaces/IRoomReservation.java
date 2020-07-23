package com.ironhack.reservationservice.controller.interfaces;

import com.ironhack.reservationservice.model.entities.RoomReservation;
import com.ironhack.reservationservice.model.viewmodel.RoomReservationViewModel;

import java.util.List;

public interface IRoomReservation {
    /**
     * Find All Room Reservations
     * @return list of RoomReservation
     */
    public List<RoomReservation> findAllRoomReservations();
    /**
     * Find Room Reservation by id
     * @param roomReservationId receives a Long with roomReservationId
     * @return a RoomReservation
     */
    public RoomReservation findRoomReservationById(Long roomReservationId);
    /**
     * Create a New Room Reservation
     * @param roomReservationViewModel receives a Room Reservation View Model
     * @return a Room Reservation
     */
    public RoomReservation newRoomReservation(RoomReservationViewModel roomReservationViewModel);
    /**
     * Cancel a Room Reservation
     * @param roomReservationId receives a Long with roomReservationId
     */
    /*
    public void removeRoomReservation(Long roomReservationId);
     */
    /**
     * User Id matches Room
     * @param userId receives a Long with userId
     * @param roomId receives an Integer with roomId
     * @return a Boolean Value
     */
    public Boolean userIdMatchesRoom(Long userId, Integer roomId);
}
