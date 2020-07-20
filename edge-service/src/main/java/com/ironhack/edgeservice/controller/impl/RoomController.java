package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.model.entities.rooms.RegularRoom;
import com.ironhack.edgeservice.model.entities.rooms.SuiteRoom;
import com.ironhack.edgeservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RegularRoom> findAllRegularRooms(@RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.findAllRegularRooms(authorizationHeader);
    }

    @GetMapping("/regular-rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegularRoom findRegularRoomById(@PathVariable("id") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.findRegularRoomById(roomId, authorizationHeader);
    }

    @PatchMapping("/update-regular-rooms/{id}/{availability}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRegularRoomAvailability(@PathVariable("id") Integer roomId, @PathVariable("availability") Boolean availability, @RequestHeader(value = "Authorization") String authorizationHeader){
        roomService.updateRegularRoomAvailability(roomId, availability, authorizationHeader);
    }

    @PostMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RegularRoom createRoom(@RequestBody RegularRoom regularRoom, @RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.createRoom(regularRoom, authorizationHeader);
    }


    // --- SUITE ROOMS ---
    @GetMapping("/suites")
    @ResponseStatus(HttpStatus.OK)
    public List<SuiteRoom> findAllSuites(@RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.findAllSuites(authorizationHeader);
    }

    @GetMapping("/suites/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SuiteRoom findSuiteById(@PathVariable("id") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.findSuiteById(roomId, authorizationHeader);
    }

    @PatchMapping("/update-suites/{id}/{availability}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSuiteAvailability(@PathVariable("id") Integer roomId, @PathVariable("availability") Boolean availability, @RequestHeader(value = "Authorization") String authorizationHeader){
        roomService.updateSuiteAvailability(roomId, availability, authorizationHeader);
    }

    @PostMapping("/suites")
    @ResponseStatus(HttpStatus.CREATED)
    public SuiteRoom createSuite(@RequestBody SuiteRoom suite, @RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.createSuite(suite, authorizationHeader);
    }
}
