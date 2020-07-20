package com.ironhack.reservationservice.client;

import com.ironhack.reservationservice.model.entities.rooms.RegularRoom;
import com.ironhack.reservationservice.model.entities.rooms.SuiteRoom;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "room-service")
public interface RoomClient {
    // --- REGULAR ROOMS ---
    @GetMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RegularRoom> findAllRegularRooms();

    @GetMapping("/regular-rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegularRoom findRegularRoomById(@PathVariable("id") Integer roomId);

    @GetMapping("/regular-rooms/availability/{available}")
    @ResponseStatus(HttpStatus.OK)
    public List<RegularRoom> findRegularRoomByAvailable(@PathVariable("available") Boolean available);

    @PatchMapping("/update-regular-rooms/{id}/{availability}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRegularRoomAvailability(@PathVariable("id") Integer roomId, @PathVariable("availability") Boolean availability);

    @PostMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RegularRoom createRoom(@RequestBody RegularRoom regularRoom);


    // --- SUITE ROOMS ---
    @GetMapping("/suites")
    @ResponseStatus(HttpStatus.OK)
    public List<SuiteRoom> findAllSuites();

    @GetMapping("/suites/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SuiteRoom findSuiteById(@PathVariable("id") Integer roomId);

    @PatchMapping("/update-suites/{id}/{availability}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSuiteAvailability(@PathVariable("id") Integer roomId, @PathVariable("availability") Boolean availability);

    @PostMapping("/suites")
    @ResponseStatus(HttpStatus.CREATED)
    public SuiteRoom createSuite(@RequestBody SuiteRoom suite);
}
