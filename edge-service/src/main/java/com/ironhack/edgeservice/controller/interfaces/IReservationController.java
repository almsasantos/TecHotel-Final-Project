package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.model.entities.reservations.RoomReservation;
import com.ironhack.edgeservice.model.viewModel.RoomReservationViewModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.util.List;

/**
 * Reservation Controller Interface
 */
public interface IReservationController {
    /**
     * Find All Room Reservations
     * @return list of RoomReservation
     */
    public List<RoomReservation> findAllRoomReservations(@RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Find Room Reservation by id
     * @param roomReservationId receives a Long with roomReservationId
     * @return a RoomReservation
     */
    public RoomReservation findRoomReservationById(@PathVariable("id") Long roomReservationId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Create a New Room Reservation
     * @param roomReservationViewModel receives a Room Reservation View Model
     * @return a Room Reservation
     */
    public RoomReservation newRoomReservation(@RequestBody @Valid RoomReservationViewModel roomReservationViewModel, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * User Id matches Room
     * @param userId receives a Long with userId
     * @param roomId receives an Integer with roomId
     * @return a Boolean Value
     */
    public Boolean userIdMatchesRoom(@PathVariable("userId") Long userId, @PathVariable("roomId") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader);
}
