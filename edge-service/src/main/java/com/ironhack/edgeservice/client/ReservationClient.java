package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.entities.reservations.RoomReservation;
import com.ironhack.edgeservice.model.viewModel.RoomReservationViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "reservation-service")
public interface ReservationClient {
    @GetMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomReservation> findAllRoomReservations();

    @GetMapping("/reservations/rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomReservation findRoomReservationById(@PathVariable("id") Long roomReservationId);

    @PostMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomReservation newRoomReservation(@RequestBody @Valid RoomReservationViewModel roomReservationViewModel);

    @DeleteMapping("/reservations/rooms/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoomReservation(@PathVariable("id") Long roomReservationId);

    @GetMapping("/reservations/user-matches-room/{userId}/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean userIdMatchesRoom(@PathVariable("userId") Long userId, @PathVariable("roomId") Integer roomId);
}
