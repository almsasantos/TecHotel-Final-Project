package com.ironhack.reservationservice.controller.interfaces;

import com.ironhack.reservationservice.model.entities.RoomReservation;
import com.ironhack.reservationservice.model.viewmodel.RoomReservationViewModel;

import java.util.List;

public interface IRoomReservation {

    public List<RoomReservation> findAllRoomReservations();

    public RoomReservation findRoomReservationById(Long roomReservationId);

    public RoomReservation newRoomReservation(RoomReservationViewModel roomReservationViewModel);

    public void removeRoomReservation(Long roomReservationId);

    public Boolean userIdMatchesRoom(Long userId, Integer roomId);
}
