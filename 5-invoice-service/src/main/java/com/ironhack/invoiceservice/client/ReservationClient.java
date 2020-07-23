package com.ironhack.invoiceservice.client;

import com.ironhack.invoiceservice.model.roomreservations.RoomReservation;
import com.ironhack.invoiceservice.model.viewmodel.RoomReservationViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "reservation-service")
public interface ReservationClient {
    /**
     * User Id matches Room
     * @param userId receives a Long with userId
     * @param roomId receives an Integer with roomId
     * @return a Boolean Value
     */
    @GetMapping("/reservations/user-matches-room/{userId}/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean userIdMatchesRoom(@PathVariable("userId") Long userId, @PathVariable("roomId") Integer roomId);

    /**
     * Create a New Room Reservation
     * @param roomReservationViewModel receives a Room Reservation View Model
     * @return a Room Reservation
     */
    @PostMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomReservation newRoomReservation(@RequestBody @Valid RoomReservationViewModel roomReservationViewModel);
}
