package com.ironhack.bookservice.controller.Impl;

import com.ironhack.bookservice.controller.Interfaces.IRegularRoomController;
import com.ironhack.bookservice.model.RegularRoom;
import com.ironhack.bookservice.service.RegularRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RegularRoomController implements IRegularRoomController {
    @Autowired
    private RegularRoomService regularRoomService;

    @GetMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RegularRoom> findAllRegularRooms(){
        return regularRoomService.findAll();
    }

    @GetMapping("/regular-rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegularRoom findRegularRoomById(@PathVariable("id") Integer roomId){
        return regularRoomService.findById(roomId);
    }

    @PatchMapping("/update-regular-rooms/{id}/{availability}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRegularRoomAvailability(@PathVariable("id") Integer roomId, @PathVariable("availability") Boolean availability){
        regularRoomService.updateRegularRoomAvailability(roomId, availability);
    }

    @PostMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RegularRoom createRoom(@RequestBody @Valid RegularRoom regularRoom){
        return regularRoomService.createRoom(regularRoom);
    }
}
