package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.model.entities.reservations.RoomReservation;
import com.ironhack.edgeservice.model.viewModel.RoomReservationViewModel;
import com.ironhack.edgeservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomReservation> findAllRoomReservations(@RequestHeader(value = "Authorization") String authorizationHeader){
        return reservationService.findAllRoomReservations(authorizationHeader);
    }

    @GetMapping("/reservations/rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomReservation findRoomReservationById(@PathVariable("id") Long roomReservationId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return reservationService.findRoomReservationById(roomReservationId, authorizationHeader);
    }

    @PostMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomReservation newRoomReservation(@RequestBody @Valid RoomReservationViewModel roomReservationViewModel, @RequestHeader(value = "Authorization") String authorizationHeader){
        return reservationService.newRoomReservation(roomReservationViewModel, authorizationHeader);
    }

    @DeleteMapping("/reservations/rooms/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoomReservation(@PathVariable("id") Long roomReservationId, @RequestHeader(value = "Authorization") String authorizationHeader){
        reservationService.removeRoomReservation(roomReservationId, authorizationHeader);
    }

    @GetMapping("/reservations/user-matches-room/{userId}/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean userIdMatchesRoom(@PathVariable("userId") Long userId, @PathVariable("roomId") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return reservationService.userIdMatchesRoom(userId, roomId, authorizationHeader);
    }
}
