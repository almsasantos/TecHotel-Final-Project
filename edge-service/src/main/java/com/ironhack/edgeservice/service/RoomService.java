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

@Service
public class RoomService {

    @Autowired
    private RoomClient roomClient;

    @Autowired
    private SecurityService securityService;

    private static final Logger LOGGER = LogManager.getLogger(RoomService.class);

    // --- REGULAR ROOMS ---
    @HystrixCommand(fallbackMethod = "findAllRegularRoomsFail", ignoreExceptions = ResponseStatusException.class)
    public List<RegularRoom> findAllRegularRooms(String authorizationHeader){
        LOGGER.info("Find All Hotel Regular Rooms");
        securityService.isUser(authorizationHeader);
        return roomClient.findAllRegularRooms();
    }

    @HystrixCommand(fallbackMethod = "findRegularRoomByIdFail", ignoreExceptions = ResponseStatusException.class)
    public RegularRoom findRegularRoomById(Integer roomId, String authorizationHeader){
        LOGGER.info("Find Hotel Regular Room with id " + roomId);
        securityService.isUser(authorizationHeader);
        return roomClient.findRegularRoomById(roomId);
    }

    @HystrixCommand(fallbackMethod = "updateRoomAvailabilityFail", ignoreExceptions = ResponseStatusException.class)
    public void updateRegularRoomAvailability(Integer roomId, Boolean availability, String authorizationHeader){
        LOGGER.info("Update Regular Room " + roomId + " with availability to " + availability);
        securityService.isUser(authorizationHeader);
        roomClient.updateRegularRoomAvailability(roomId, availability);
    }

    @HystrixCommand(fallbackMethod = "createRoomFail", ignoreExceptions = ResponseStatusException.class)
    public RegularRoom createRoom(RegularRoom regularRoom, String authorizationHeader){
        LOGGER.info("Create new Hotel Regular Room");
        securityService.isUser(authorizationHeader);
        return roomClient.createRoom(regularRoom);
    }

    // --- SUITE ROOMS ---
    @HystrixCommand(fallbackMethod = "findAllSuitesFail", ignoreExceptions = ResponseStatusException.class)
    public List<SuiteRoom> findAllSuites(String authorizationHeader){
        LOGGER.info("Find All Hotel Suite Rooms");
        securityService.isUser(authorizationHeader);
        return roomClient.findAllSuites();
    }

    @HystrixCommand(fallbackMethod = "findSuiteByIdFail", ignoreExceptions = ResponseStatusException.class)
    public SuiteRoom findSuiteById(Integer roomId, String authorizationHeader){
        LOGGER.info("Find Hotel Suite Room with id " + roomId);
        securityService.isUser(authorizationHeader);
        return roomClient.findSuiteById(roomId);
    }

    @HystrixCommand(fallbackMethod = "updateRoomAvailabilityFail", ignoreExceptions = ResponseStatusException.class)
    public void updateSuiteAvailability(Integer roomId, Boolean availability, String authorizationHeader){
        LOGGER.info("Update Suite Room " + roomId + " with availability to " + availability);
        securityService.isUser(authorizationHeader);
        roomClient.updateSuiteAvailability(roomId, availability);
    }

    @HystrixCommand(fallbackMethod = "createSuiteFail", ignoreExceptions = ResponseStatusException.class)
    public SuiteRoom createSuite(SuiteRoom suite, String authorizationHeader){
        LOGGER.info("Create new Hotel Suite Room");
        securityService.isUser(authorizationHeader);
        return roomClient.createSuite(suite);
    }

    // --- FallBack Methods ---
    public List<RegularRoom> findAllRegularRoomsFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find All Hotel Regular Rooms");
        return null;
    }

    public RegularRoom findRegularRoomByIdFail(Integer roomId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Hotel Regular Room by id");
        return null;
    }

    public void updateRoomAvailabilityFail(Integer roomId, Boolean availability, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update availability of Room " + roomId);
    }

    public RegularRoom createRoomFail(RegularRoom regularRoom, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to create a new Hotel Regular Room");
        return null;
    }

    public List<SuiteRoom> findAllSuitesFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find All Hotel Suite Rooms");
        return null;
    }

    public SuiteRoom findSuiteByIdFail(Integer roomId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Suite Regular Room by id");
        return null;
    }

    public SuiteRoom createSuiteFail(SuiteRoom suite, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to create a new Hotel Suite Room");
        return null;
    }

}
