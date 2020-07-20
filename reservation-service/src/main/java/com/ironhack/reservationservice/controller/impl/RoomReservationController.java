
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

@RestController
public class RoomReservationController implements IRoomReservation {
    @Autowired
    private RoomReservationService roomReservationService;

    @GetMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomReservation> findAllRoomReservations(){
        return roomReservationService.findAllRoomReservations();
    }

    @GetMapping("/reservations/rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomReservation findRoomReservationById(@PathVariable("id") Long roomReservationId){
        return roomReservationService.findRoomReservationById(roomReservationId);
    }

    @PostMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomReservation newRoomReservation(@RequestBody @Valid RoomReservationViewModel roomReservationViewModel){
        return roomReservationService.newRoomReservation(roomReservationViewModel);
    }

    @DeleteMapping("/reservations/rooms/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoomReservation(@PathVariable("id") Long roomReservationId){
        roomReservationService.removeRoomReservation(roomReservationId);
    }

    @GetMapping("/reservations/user-matches-room/{userId}/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean userIdMatchesRoom(@PathVariable("userId") Long userId, @PathVariable("roomId") Integer roomId){
        return roomReservationService.userIdMatchesRoom(userId, roomId);
    }

}
