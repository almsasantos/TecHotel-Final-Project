package com.ironhack.bookservice.controller.Interfaces;

import com.ironhack.bookservice.model.RegularRoom;

import java.util.List;

/**
 * Regular Room Controller Interface
 */
public interface IRegularRoomController {
    /**
     * Find All Regular Rooms
     * @return a list of RegularRoom
     */
    public List<RegularRoom> findAllRegularRooms();

    /**
     * Find Regular Room by Id
     * @param roomId receives an Integer with roomId
     * @return a RegularRoom
     */
    public RegularRoom findRegularRoomById(Integer roomId);

    /**
     * Update Regular Room Availability
     * @param roomId receives an Integer with roomId
     * @param availability receives a Boolean with availability
     */
    public void updateRegularRoomAvailability(Integer roomId, Boolean availability);

    /**
     * Create new Regular Room
     * @param regularRoom receives a RegularRoom
     * @return a RegularRoom created
     */
    public RegularRoom createRoom(RegularRoom regularRoom);
}
