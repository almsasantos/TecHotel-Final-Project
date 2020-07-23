package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.model.entities.rooms.RegularRoom;
import com.ironhack.edgeservice.model.entities.rooms.SuiteRoom;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * Room Controller Interface
 */
public interface IRoomController {
    /**
     * Find All Regular Rooms
     * @return a list of RegularRoom
     */
    public List<RegularRoom> findAllRegularRooms(@RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Find Regular Room by Id
     * @param roomId receives an Integer with roomId
     * @return a RegularRoom
     */
    public RegularRoom findRegularRoomById(@PathVariable("id") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Update Regular Room Availability
     * @param roomId receives an Integer with roomId
     * @param availability receives a Boolean with availability
     */
    public void updateRegularRoomAvailability(@PathVariable("id") Integer roomId, @PathVariable("availability") Boolean availability, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Create new Regular Room
     * @param regularRoom receives a RegularRoom
     * @return a RegularRoom created
     */
    public RegularRoom createRoom(@RequestBody RegularRoom regularRoom, @RequestHeader(value = "Authorization") String authorizationHeader);


    // --- SUITE ROOMS ---
    /**
     * Find All Suites
     * @return a list of SuiteRoom
     */
    public List<SuiteRoom> findAllSuites(@RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Find Suite by id
     * @param roomId receives an Integer with roomId
     * @return a SuiteRoom
     */
    public SuiteRoom findSuiteById(@PathVariable("id") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Update Suite Availability
     * @param roomId receives an Integer with roomId
     * @param availability receives a Boolean with availability
     */
    public void updateSuiteAvailability(@PathVariable("id") Integer roomId, @PathVariable("availability") Boolean availability, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Creates a Suite
     * @param suite receives a SuiteRoom
     * @return a SuiteRoom created
     */
    public SuiteRoom createSuite(@RequestBody SuiteRoom suite, @RequestHeader(value = "Authorization") String authorizationHeader);
}
