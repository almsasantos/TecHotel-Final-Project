package com.ironhack.bookservice.controller.Impl;

import com.ironhack.bookservice.controller.Interfaces.IRegularRoomController;
import com.ironhack.bookservice.model.RegularRoom;
import com.ironhack.bookservice.service.RegularRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Regular Room's Controller
 */
@RestController
public class RegularRoomController implements IRegularRoomController {
    /**
     * Autowired of Regular Room Service
     */
    @Autowired
    private RegularRoomService regularRoomService;

    /**
     * Find All Regular Rooms
     * @return a list of RegularRoom
     */
    @GetMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RegularRoom> findAllRegularRooms(){
        return regularRoomService.findAll();
    }

    /**
     * Find Regular Room by Id
     * @param roomId receives an Integer with roomId
     * @return a RegularRoom
     */
    @GetMapping("/regular-rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegularRoom findRegularRoomById(@PathVariable("id") Integer roomId){
        return regularRoomService.findById(roomId);
    }

    /**
     * Update Regular Room Availability
     * @param roomId receives an Integer with roomId
     * @param availability receives a Boolean with availability
     */
    @PatchMapping("/update-regular-rooms/{id}/{availability}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRegularRoomAvailability(@PathVariable("id") Integer roomId, @PathVariable("availability") Boolean availability){
        regularRoomService.updateRegularRoomAvailability(roomId, availability);
    }

    /**
     * Create new Regular Room
     * @param regularRoom receives a RegularRoom
     * @return a RegularRoom created
     */
    @PostMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RegularRoom createRoom(@RequestBody @Valid RegularRoom regularRoom){
        return regularRoomService.createRoom(regularRoom);
    }
}
