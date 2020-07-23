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
    /**
     * Find All Invoices
     * @return a list of invoices
     */
    @GetMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomReservation> findAllRoomReservations();
    /**
     * Find Room Reservation by id
     * @param roomReservationId receives a Long with roomReservationId
     * @return a RoomReservation
     */
    @GetMapping("/reservations/rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomReservation findRoomReservationById(@PathVariable("id") Long roomReservationId);
    /**
     * Create a New Room Reservation
     * @param roomReservationViewModel receives a Room Reservation View Model
     * @return a Room Reservation
     */
    @PostMapping("/reservations/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomReservation newRoomReservation(@RequestBody @Valid RoomReservationViewModel roomReservationViewModel);
    /**
     * User Id matches Room
     * @param userId receives a Long with userId
     * @param roomId receives an Integer with roomId
     * @return a Boolean Value
     */
    @GetMapping("/reservations/user-matches-room/{userId}/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean userIdMatchesRoom(@PathVariable("userId") Long userId, @PathVariable("roomId") Integer roomId);
}
