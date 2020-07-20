package com.ironhack.bookservice.service;

import com.ironhack.bookservice.exception.DataNotFoundException;
import com.ironhack.bookservice.model.RegularRoom;
import com.ironhack.bookservice.repository.RegularRoomRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegularRoomService {
    @Autowired
    private RegularRoomRepository regularRoomRepository;

    private static final Logger LOGGER = LogManager.getLogger(RegularRoomService.class);

    public List<RegularRoom> findAll(){
        LOGGER.info("Find All Regular Hotel Rooms");
        return regularRoomRepository.findAll();
    }

    public RegularRoom findById(Integer roomId){
        LOGGER.info("Find Regular Hotel Room with id " + roomId);
        return regularRoomRepository.findById(roomId).orElseThrow(() -> new DataNotFoundException("Room id not found"));
    }

    public void updateRegularRoomAvailability(Integer roomId, Boolean available){
        LOGGER.info("[INIT] Update Regular Room availability with id " + roomId +" to " + available);
        RegularRoom regularRoom = findById(roomId);
        regularRoom.setAvailable(available);
        regularRoomRepository.save(regularRoom);
        LOGGER.info("[END] Update Regular Room availability with id " + roomId +" to " + available);
    }

    public RegularRoom createRoom(RegularRoom regularRoom){
        LOGGER.info("Create new Regular Hotel Room");
        return regularRoomRepository.save(regularRoom);
    }
}
