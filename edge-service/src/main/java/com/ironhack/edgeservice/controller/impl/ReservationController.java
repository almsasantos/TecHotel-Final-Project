package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.interfaces.IReservationController;
import com.ironhack.edgeservice.model.entities.reservations.RoomReservation;
import com.ironhack.edgeservice.model.viewModel.RoomReservationViewModel;
import com.ironhack.edgeservice.service.ReservationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Reservation Controller")
@RestController
@RequestMapping("/")
public class ReservationController implements IReservationController {
    @Autowired
    private ReservationService reservationService;
    /**
     * Find All Room Reservations
     * @return list of RoomReservation
     */
    @GetMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomReservation> findAllRoomReservations(@RequestHeader(value = "Authorization") String authorizationHeader){
        return reservationService.findAllRoomReservations(authorizationHeader);
    }
    /**
     * Find Room Reservation by id
     * @param roomReservationId receives a Long with roomReservationId
     * @return a RoomReservation
     */
    @GetMapping("/reservations/rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomReservation findRoomReservationById(@PathVariable("id") Long roomReservationId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return reservationService.findRoomReservationById(roomReservationId, authorizationHeader);
    }
    /**
     * Create a New Room Reservation
     * @param roomReservationViewModel receives a Room Reservation View Model
     * @return a Room Reservation
     */
    @PostMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomReservation newRoomReservation(@RequestBody @Valid RoomReservationViewModel roomReservationViewModel, @RequestHeader(value = "Authorization") String authorizationHeader){
        return reservationService.newRoomReservation(roomReservationViewModel, authorizationHeader);
    }

    /**
     * User Id matches Room
     * @param userId receives a Long with userId
     * @param roomId receives an Integer with roomId
     * @return a Boolean Value
     */
    @GetMapping("/reservations/user-matches-room/{userId}/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean userIdMatchesRoom(@PathVariable("userId") Long userId, @PathVariable("roomId") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return reservationService.userIdMatchesRoom(userId, roomId, authorizationHeader);
    }
}
