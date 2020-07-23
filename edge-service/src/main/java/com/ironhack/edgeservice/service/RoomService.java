package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.RoomClient;
import com.ironhack.edgeservice.model.entities.rooms.RegularRoom;
import com.ironhack.edgeservice.model.entities.rooms.SuiteRoom;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Room Service
 */
@Service
public class RoomService {

    /**
     * Autowired of RoomClient
     */
    @Autowired
    private RoomClient roomClient;
    /**
     * Autowired of SecurityService
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Final variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(RoomService.class);

    // --- REGULAR ROOMS ---
    /**
     * Find All Regular Rooms
     * @return a list of RegularRoom
     */
    @HystrixCommand(fallbackMethod = "findAllRegularRoomsFail", ignoreExceptions = ResponseStatusException.class)
    public List<RegularRoom> findAllRegularRooms(String authorizationHeader){
        LOGGER.info("Find All Hotel Regular Rooms");
        securityService.isUser(authorizationHeader);
        return roomClient.findAllRegularRooms(); }

    /**
     * Find Regular Room by Id
     * @param roomId receives an Integer with roomId
     * @return a RegularRoom
     */
    @HystrixCommand(fallbackMethod = "findRegularRoomByIdFail", ignoreExceptions = ResponseStatusException.class)
    public RegularRoom findRegularRoomById(Integer roomId, String authorizationHeader){
        LOGGER.info("Find Hotel Regular Room with id " + roomId);
        securityService.isUser(authorizationHeader);
        return roomClient.findRegularRoomById(roomId); }

    /**
     * Update Regular Room Availability
     * @param roomId receives an Integer with roomId
     * @param availability receives a Boolean with availability
     */
    @HystrixCommand(fallbackMethod = "updateRoomAvailabilityFail", ignoreExceptions = ResponseStatusException.class)
    public void updateRegularRoomAvailability(Integer roomId, Boolean availability, String authorizationHeader){
        LOGGER.info("Update Regular Room " + roomId + " with availability to " + availability);
        securityService.isUser(authorizationHeader);
        roomClient.updateRegularRoomAvailability(roomId, availability); }

    /**
     * Create new Regular Room
     * @param regularRoom receives a RegularRoom
     * @return a RegularRoom created
     */
    @HystrixCommand(fallbackMethod = "createRoomFail", ignoreExceptions = ResponseStatusException.class)
    public RegularRoom createRoom(RegularRoom regularRoom, String authorizationHeader){
        LOGGER.info("Create new Hotel Regular Room");
        securityService.isUser(authorizationHeader);
        return roomClient.createRoom(regularRoom); }

    // --- SUITE ROOMS ---
    /**
     * Find All Suites
     * @return a list of SuiteRoom
     */
    @HystrixCommand(fallbackMethod = "findAllSuitesFail", ignoreExceptions = ResponseStatusException.class)
    public List<SuiteRoom> findAllSuites(String authorizationHeader){
        LOGGER.info("Find All Hotel Suite Rooms");
        securityService.isUser(authorizationHeader);
        return roomClient.findAllSuites(); }

    /**
     * Find Suite by id
     * @param roomId receives an Integer with roomId
     * @return a SuiteRoom
     */
    @HystrixCommand(fallbackMethod = "findSuiteByIdFail", ignoreExceptions = ResponseStatusException.class)
    public SuiteRoom findSuiteById(Integer roomId, String authorizationHeader){
        LOGGER.info("Find Hotel Suite Room with id " + roomId);
        securityService.isUser(authorizationHeader);
        return roomClient.findSuiteById(roomId); }

    /**
     * Update Suite Availability
     * @param roomId receives an Integer with roomId
     * @param availability receives a Boolean with availability
     */
    @HystrixCommand(fallbackMethod = "updateRoomAvailabilityFail", ignoreExceptions = ResponseStatusException.class)
    public void updateSuiteAvailability(Integer roomId, Boolean availability, String authorizationHeader){
        LOGGER.info("Update Suite Room " + roomId + " with availability to " + availability);
        securityService.isUser(authorizationHeader);
        roomClient.updateSuiteAvailability(roomId, availability); }

    /**
     * Creates a Suite
     * @param suite receives a SuiteRoom
     * @return a SuiteRoom created
     */
    @HystrixCommand(fallbackMethod = "createSuiteFail", ignoreExceptions = ResponseStatusException.class)
    public SuiteRoom createSuite(SuiteRoom suite, String authorizationHeader){
        LOGGER.info("Create new Hotel Suite Room");
        securityService.isUser(authorizationHeader);
        return roomClient.createSuite(suite); }

    // --- FallBack Methods ---
    public List<RegularRoom> findAllRegularRoomsFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find All Hotel Regular Rooms");
        return null; }

    public RegularRoom findRegularRoomByIdFail(Integer roomId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Hotel Regular Room by id");
        return null;}

    public void updateRoomAvailabilityFail(Integer roomId, Boolean availability, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update availability of Room " + roomId); }

    public RegularRoom createRoomFail(RegularRoom regularRoom, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to create a new Hotel Regular Room");
        return null; }

    public List<SuiteRoom> findAllSuitesFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find All Hotel Suite Rooms");
        return null; }

    public SuiteRoom findSuiteByIdFail(Integer roomId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Suite Regular Room by id");
        return null; }

    public SuiteRoom createSuiteFail(SuiteRoom suite, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to create a new Hotel Suite Room");
        return null; }

}
