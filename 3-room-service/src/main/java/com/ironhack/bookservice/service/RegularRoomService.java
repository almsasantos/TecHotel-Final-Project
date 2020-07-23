package com.ironhack.bookservice.service;

import com.ironhack.bookservice.exception.DataNotFoundException;
import com.ironhack.bookservice.model.RegularRoom;
import com.ironhack.bookservice.repository.RegularRoomRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Regular Room Service
 */
@Service
public class RegularRoomService {
    /**
     * Autowired of Regular Room Repository
     */
    @Autowired
    private RegularRoomRepository regularRoomRepository;

    /**
     * Final variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(RegularRoomService.class);

    /**
     * Find All Regular Rooms
     * @return a list of RegularRoom
     */
    public List<RegularRoom> findAll(){
        LOGGER.info("Find All Regular Hotel Rooms");
        return regularRoomRepository.findAll();
    }

    /**
     * Find Regular Room by id
     * @param roomId receives an Integer with roomId
     * @return a RegularRoom
     */
    public RegularRoom findById(Integer roomId){
        LOGGER.info("Find Regular Hotel Room with id " + roomId);
        return regularRoomRepository.findById(roomId).orElseThrow(() -> new DataNotFoundException("Room id not found"));
    }

    /**
     * Update Regular Room Availability
     * @param roomId receives an Integer with roomId
     * @param available receives a Boolean with available
     */
    public void updateRegularRoomAvailability(Integer roomId, Boolean available){
        LOGGER.info("[INIT] Update Regular Room availability with id " + roomId +" to " + available);
        RegularRoom regularRoom = findById(roomId);
        regularRoom.setAvailable(available);
        regularRoomRepository.save(regularRoom);
        LOGGER.info("[END] Update Regular Room availability with id " + roomId +" to " + available);
    }

    /**
     * Create a new Regular Room
     * @param regularRoom receives a RegularRoom
     * @return a RegularRoom Created
     */
    public RegularRoom createRoom(RegularRoom regularRoom){
        LOGGER.info("Create new Regular Hotel Room");
        return regularRoomRepository.save(regularRoom);
    }
}
