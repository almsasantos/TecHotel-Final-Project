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

/**
 * Reservation Service
 */
@Service
public class ReservationService {
    /**
     * Autowired of ReservationClient
     */
    @Autowired
    private ReservationClient reservationClient;
    /**
     * Autowired of SecurityService
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Final variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(ReservationService.class);

    /**
     * Find All Room Reservations
     * @return list of RoomReservation
     */
    @HystrixCommand(fallbackMethod = "findAllRoomReservationsFail", ignoreExceptions = ResponseStatusException.class)
    public List<RoomReservation> findAllRoomReservations(String authorizationHeader){
        LOGGER.info("Find All Room Reservations");
        securityService.isUser(authorizationHeader);
        return reservationClient.findAllRoomReservations(); }

    /**
     * Find Room Reservation by id
     * @param roomReservationId receives a Long with roomReservationId
     * @return a RoomReservation
     */
    @HystrixCommand(fallbackMethod = "findRoomReservationByIdFail", ignoreExceptions = ResponseStatusException.class)
    public RoomReservation findRoomReservationById(Long roomReservationId, String authorizationHeader){
        LOGGER.info("Find Room Reservation with id " + roomReservationId);
        securityService.isUser(authorizationHeader);
        return reservationClient.findRoomReservationById(roomReservationId); }

    /**
     * Create a New Room Reservation
     * @param roomReservationViewModel receives a Room Reservation View Model
     * @return a Room Reservation
     */
    @HystrixCommand(fallbackMethod = "newRoomReservationFail", ignoreExceptions = ResponseStatusException.class)
    public RoomReservation newRoomReservation(RoomReservationViewModel roomReservationViewModel, String authorizationHeader){
        LOGGER.info("Create new Room Reservation");
        securityService.isUser(authorizationHeader);
        return reservationClient.newRoomReservation(roomReservationViewModel); }

    /**
     * User Id matches Room
     * @param userId receives a Long with userId
     * @param roomId receives an Integer with roomId
     * @return a Boolean Value
     */
    @HystrixCommand(fallbackMethod = "userIdMatchesRoomFail", ignoreExceptions = ResponseStatusException.class)
    public Boolean userIdMatchesRoom(Long userId, Integer roomId, String authorizationHeader){
        LOGGER.info("Check if user " + userId + "is in room " + roomId);
        securityService.isUser(authorizationHeader);
        return reservationClient.userIdMatchesRoom(userId, roomId); }

    public List<RoomReservation> findAllRoomReservationsFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Room Reservations");
        return null; }

    public RoomReservation findRoomReservationByIdFail(Long roomReservationId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Room Reservation with id " + roomReservationId);
        return null; }

    public RoomReservation newRoomReservationFail(RoomReservationViewModel roomReservationViewModel, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to create new Room Reservation");
        return null; }

    public Boolean userIdMatchesRoomFail(Long userId, Integer roomId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to check if user " + userId + " was in room " + roomId);
        return null; }
}
