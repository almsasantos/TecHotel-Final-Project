package com.ironhack.invoiceservice.client;

import com.ironhack.invoiceservice.model.rooms.RegularRoom;
import com.ironhack.invoiceservice.model.rooms.SuiteRoom;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "room-service")
public interface RoomClient {
    /**
     * Find All Regular Rooms
     * @return a list of RegularRoom
     */
    @GetMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RegularRoom> findAllRegularRooms();

    /**
     * Find Regular Room by Id
     * @param roomId receives an Integer with roomId
     * @return a RegularRoom
     */
    @GetMapping("/regular-rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegularRoom findRegularRoomById(@PathVariable("id") Integer roomId);
    /**
     * Find All Suites
     * @return a list of SuiteRoom
     */
    @GetMapping("/suites")
    @ResponseStatus(HttpStatus.OK)
    public List<SuiteRoom> findAllSuites();
}
