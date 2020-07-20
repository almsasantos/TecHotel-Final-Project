package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.ReservationClient;
import com.ironhack.edgeservice.model.entities.reservations.RoomReservation;
import com.ironhack.edgeservice.model.viewModel.RoomReservationViewModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationClient reservationClient;

    @Autowired
    private SecurityService securityService;

    private static final Logger LOGGER = LogManager.getLogger(ReservationService.class);

    @HystrixCommand(fallbackMethod = "findAllRoomReservationsFail", ignoreExceptions = ResponseStatusException.class)
    public List<RoomReservation> findAllRoomReservations(String authorizationHeader){
        LOGGER.info("Find All Room Reservations");
        securityService.isUser(authorizationHeader);
        return reservationClient.findAllRoomReservations();
    }

    @HystrixCommand(fallbackMethod = "findRoomReservationByIdFail", ignoreExceptions = ResponseStatusException.class)
    public RoomReservation findRoomReservationById(Long roomReservationId, String authorizationHeader){
        LOGGER.info("Find Room Reservation with id " + roomReservationId);
        securityService.isUser(authorizationHeader);
        return reservationClient.findRoomReservationById(roomReservationId);
    }

    @HystrixCommand(fallbackMethod = "newRoomReservationFail", ignoreExceptions = ResponseStatusException.class)
    public RoomReservation newRoomReservation(RoomReservationViewModel roomReservationViewModel, String authorizationHeader){
        LOGGER.info("Create new Room Reservation");
        securityService.isUser(authorizationHeader);
        return reservationClient.newRoomReservation(roomReservationViewModel);
    }

    @HystrixCommand(fallbackMethod = "removeRoomReservationFail", ignoreExceptions = ResponseStatusException.class)
    public void removeRoomReservation(Long roomReservationId, String authorizationHeader){
        LOGGER.info("Cancel the Room Reservation with id " + roomReservationId);
        securityService.isUser(authorizationHeader);
        reservationClient.removeRoomReservation(roomReservationId);
    }

    @HystrixCommand(fallbackMethod = "userIdMatchesRoomFail", ignoreExceptions = ResponseStatusException.class)
    public Boolean userIdMatchesRoom(Long userId, Integer roomId, String authorizationHeader){
        LOGGER.info("Check if user " + userId + "is in room " + roomId);
        securityService.isUser(authorizationHeader);
        return reservationClient.userIdMatchesRoom(userId, roomId);
    }

    public List<RoomReservation> findAllRoomReservationsFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Room Reservations");
        return null;
    }

    public RoomReservation findRoomReservationByIdFail(Long roomReservationId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Room Reservation with id " + roomReservationId);
        return null;
    }

    public RoomReservation newRoomReservationFail(RoomReservationViewModel roomReservationViewModel, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to create new Room Reservation");
        return null;
    }

    public void removeRoomReservationFail(Long roomReservationId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to cancel Room Reservation " + roomReservationId);
    }

    public Boolean userIdMatchesRoomFail(Long userId, Integer roomId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to check if user " + userId + " was in room " + roomId);
        return null;
    }
}
