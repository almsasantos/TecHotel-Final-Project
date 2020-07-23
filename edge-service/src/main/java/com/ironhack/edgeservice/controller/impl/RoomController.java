package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.interfaces.IRoomController;
import com.ironhack.edgeservice.model.entities.rooms.RegularRoom;
import com.ironhack.edgeservice.model.entities.rooms.SuiteRoom;
import com.ironhack.edgeservice.service.RoomService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Room Controller")
@RestController
@RequestMapping("/")
public class RoomController implements IRoomController {
    @Autowired
    private RoomService roomService;
    /**
     * Find All Regular Rooms
     * @return a list of RegularRoom
     */
    @GetMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RegularRoom> findAllRegularRooms(@RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.findAllRegularRooms(authorizationHeader);
    }
    /**
     * Find Regular Room by Id
     * @param roomId receives an Integer with roomId
     * @return a RegularRoom
     */
    @GetMapping("/regular-rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegularRoom findRegularRoomById(@PathVariable("id") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.findRegularRoomById(roomId, authorizationHeader);
    }
    /**
     * Update Regular Room Availability
     * @param roomId receives an Integer with roomId
     * @param availability receives a Boolean with availability
     */
    @PatchMapping("/update-regular-rooms/{id}/{availability}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRegularRoomAvailability(@PathVariable("id") Integer roomId, @PathVariable("availability") Boolean availability, @RequestHeader(value = "Authorization") String authorizationHeader){
        roomService.updateRegularRoomAvailability(roomId, availability, authorizationHeader); }

    /**
     * Create new Regular Room
     * @param regularRoom receives a RegularRoom
     * @return a RegularRoom created
     */
    @PostMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RegularRoom createRoom(@RequestBody RegularRoom regularRoom, @RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.createRoom(regularRoom, authorizationHeader);
    }


    // --- SUITE ROOMS ---
    /**
     * Find All Suites
     * @return a list of SuiteRoom
     */
    @GetMapping("/suites")
    @ResponseStatus(HttpStatus.OK)
    public List<SuiteRoom> findAllSuites(@RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.findAllSuites(authorizationHeader);
    }
    /**
     * Find Suite by id
     * @param roomId receives an Integer with roomId
     * @return a SuiteRoom
     */
    @GetMapping("/suites/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SuiteRoom findSuiteById(@PathVariable("id") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.findSuiteById(roomId, authorizationHeader);
    }
    /**
     * Update Suite Availability
     * @param roomId receives an Integer with roomId
     * @param availability receives a Boolean with availability
     */
    @PatchMapping("/update-suites/{id}/{availability}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSuiteAvailability(@PathVariable("id") Integer roomId, @PathVariable("availability") Boolean availability, @RequestHeader(value = "Authorization") String authorizationHeader){
        roomService.updateSuiteAvailability(roomId, availability, authorizationHeader);}

    /**
     * Creates a Suite
     * @param suite receives a SuiteRoom
     * @return a SuiteRoom created
     */
    @PostMapping("/suites")
    @ResponseStatus(HttpStatus.CREATED)
    public SuiteRoom createSuite(@RequestBody SuiteRoom suite, @RequestHeader(value = "Authorization") String authorizationHeader){
        return roomService.createSuite(suite, authorizationHeader);
    }
}
