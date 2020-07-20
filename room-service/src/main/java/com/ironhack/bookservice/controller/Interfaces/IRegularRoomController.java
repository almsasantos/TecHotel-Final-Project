package com.ironhack.bookservice.controller.Interfaces;

import com.ironhack.bookservice.model.RegularRoom;

import java.util.List;

public interface IRegularRoomController {
    public List<RegularRoom> findAllRegularRooms();

    public RegularRoom findRegularRoomById(Integer roomId);

    public void updateRegularRoomAvailability(Integer roomId, Boolean availability);

    public RegularRoom createRoom(RegularRoom regularRoom);
}
