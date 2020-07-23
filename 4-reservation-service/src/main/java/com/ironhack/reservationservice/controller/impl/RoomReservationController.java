
package com.ironhack.reservationservice.controller.impl;

import com.ironhack.reservationservice.controller.interfaces.IRoomReservation;
import com.ironhack.reservationservice.model.entities.RoomReservation;
import com.ironhack.reservationservice.model.viewmodel.RoomReservationViewModel;
import com.ironhack.reservationservice.service.RoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Room Reservation Controller
 */
@RestController
public class RoomReservationController implements IRoomReservation {
    /**
     * Autowired of Room Reservation Service
     */
    @Autowired
    private RoomReservationService roomReservationService;

    /**
     * Find All Room Reservations
     * @return list of RoomReservation
     */
    @GetMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomReservation> findAllRoomReservations(){
        return roomReservationService.findAllRoomReservations();
    }

    /**
     * Find Room Reservation by id
     * @param roomReservationId receives a Long with roomReservationId
     * @return a RoomReservation
     */
    @GetMapping("/reservations/rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomReservation findRoomReservationById(@PathVariable("id") Long roomReservationId){
        return roomReservationService.findRoomReservationById(roomReservationId);
    }
    /**
     * Create a New Room Reservation
     * @param roomReservationViewModel receives a Room Reservation View Model
     * @return a Room Reservation
     */
    @PostMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomReservation newRoomReservation(@RequestBody @Valid RoomReservationViewModel roomReservationViewModel){
        return roomReservationService.newRoomReservation(roomReservationViewModel);
    }

    /**
     * Cancel a Room Reservation
     * @param roomReservationId receives a Long with roomReservationId
     */
    /*
    @DeleteMapping("/reservations/rooms/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoomReservation(@PathVariable("id") Long roomReservationId){
        roomReservationService.removeRoomReservation(roomReservationId);
    }
     */
    /**
     * User Id matches Room
     * @param userId receives a Long with userId
     * @param roomId receives an Integer with roomId
     * @return a Boolean Value
     */
    @GetMapping("/reservations/user-matches-room/{userId}/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean userIdMatchesRoom(@PathVariable("userId") Long userId, @PathVariable("roomId") Integer roomId){
        return roomReservationService.userIdMatchesRoom(userId, roomId);
    }

}
